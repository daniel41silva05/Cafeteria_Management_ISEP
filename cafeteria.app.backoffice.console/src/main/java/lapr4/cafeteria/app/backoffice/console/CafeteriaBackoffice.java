package lapr4.cafeteria.app.backoffice.console;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import lapr4.cafeteria.app.backoffice.console.presentation.MainMenu;
import lapr4.cafeteria.app.common.console.CafeteriaBaseApplication;
import lapr4.cafeteria.app.common.console.presentation.LoginUI;
import lapr4.cafeteria.infrastructure.AuthenticationCredentialHandler;
import lapr4.cafeteria.infrastructure.persistence.PersistenceContext;
import lapr4.cafeteria.user_management.domain.CafeteriaPasswordPolicy;

@SuppressWarnings("squid:S106")
public final class CafeteriaBackoffice extends CafeteriaBaseApplication {

    /**
     * avoid instantiation of this class.
     */
    private CafeteriaBackoffice() {
        super();
    }

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(final String[] args) {

        new CafeteriaBackoffice().run(args);
    }

    @Override
    protected void doMain(final String[] args) {
        // login and go to main menu
        final boolean authenticated = new LoginUI(new AuthenticationCredentialHandler()).show();
        if (authenticated) {
            // go to main menu
            final var menu = new MainMenu();
            menu.mainLoop();
        }
    }

    @Override
    protected String appTitle() {
        return "Cafeteria Back Office";
    }

    @Override
    protected String appGoodbye() {
        return "Cafeteria Back Office";
    }

    @Override
    protected void configureAuthz() {
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new CafeteriaPasswordPolicy(),
                new PlainTextEncoder());
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
    }
}
