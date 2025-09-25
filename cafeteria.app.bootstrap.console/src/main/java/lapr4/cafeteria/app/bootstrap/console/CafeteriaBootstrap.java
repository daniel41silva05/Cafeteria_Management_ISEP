package lapr4.cafeteria.app.bootstrap.console;

import eapli.framework.collections.util.ArrayPredicates;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import eapli.framework.io.util.Console;
import lapr4.cafeteria.app.common.console.CafeteriaBaseApplication;
import lapr4.cafeteria.infrastructure.bootstrapers.CafeteriaBootstrapper;
import lapr4.cafeteria.infrastructure.bootstrapers.demo.CafeteriaDemoBootstrapper;
import lapr4.cafeteria.infrastructure.persistence.PersistenceContext;
import lapr4.cafeteria.user_management.domain.CafeteriaPasswordPolicy;

@SuppressWarnings("squid:S106")
public final class CafeteriaBootstrap extends CafeteriaBaseApplication {

    private boolean isToBootstrapDemoData;
    private boolean isToRunSampleE2E;
    private boolean isToWaitInTheEnd;

    /**
     * avoid instantiation of this class.
     */
    private CafeteriaBootstrap() {
        super();
    }

    public static void main(final String[] args) {

        new CafeteriaBootstrap().run(args);
    }

    @Override
    protected void doMain(final String[] args) {
        handleArgs(args);

        System.out.println("\n\n------- MASTER DATA -------");
        new CafeteriaBootstrapper().execute();

        if (isToBootstrapDemoData) {
            System.out.println("\n\n------- DEMO DATA -------");
            new CafeteriaDemoBootstrapper().execute();
        }

        if (isToRunSampleE2E) {
//            System.out.println("\n\n------- BASIC SCENARIO -------");
//            new ShodroneDemoSmokeTester().execute();
        }

        if (isToWaitInTheEnd) {
            Console.readLine("\n\n>>>>>> Enter to finish the program.");
        }
    }

    private void handleArgs(final String[] args) {
        isToRunSampleE2E = ArrayPredicates.contains(args, "-smoke:e2e");
        if (isToRunSampleE2E) {
            isToBootstrapDemoData = true;
        } else {
            isToBootstrapDemoData = ArrayPredicates.contains(args, "-bootstrap:demo");
        }

        isToWaitInTheEnd = ArrayPredicates.contains(args, "-wait");
    }

    @Override
    protected String appTitle() {
        return "Bootstrapping Cafeteria data ";
    }

    @Override
    protected String appGoodbye() {
        return "Bootstrap data done.";
    }

    @Override
    protected void configureAuthz() {
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new CafeteriaPasswordPolicy(),
                new PlainTextEncoder());
    }

    @Override
    protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
    }

}
