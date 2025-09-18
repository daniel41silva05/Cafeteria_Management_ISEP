package lapr4.cafeteria.app.backoffice.console.presentation.users;

import eapli.framework.visitor.Visitor;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;

@SuppressWarnings({ "squid:S106" })
public class CafeteriaUserPrinter implements Visitor<CafeteriaUser> {

    @Override
    public void visit(final CafeteriaUser visitee) {
        System.out.printf(
                "%-35s%-40s%-15s%n",
                visitee.identity(),
                visitee.user().name(),
                visitee.user().isActive() ? "Active" : "Inactive"
        );
    }

    public String header() {
        return String.format(
                "%-35s%-40s%-15s%n",
                "EMAIL", "NAME", "STATUS"
        );
    }

}
