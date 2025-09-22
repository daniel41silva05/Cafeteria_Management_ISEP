package lapr4.cafeteria.app.backoffice.console.presentation.users;

import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;
import lapr4.cafeteria.user_management.application.ListUsersController;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;

@SuppressWarnings({ "squid:S106" })
public class ListUsersUI extends AbstractListUI<CafeteriaUser> {

    private final ListUsersController controller = new ListUsersController();

    @Override
    public String headline() {
        return "List Users";
    }

    @Override
    protected String emptyMessage() {
        return "No data.";
    }

    @Override
    protected Iterable<CafeteriaUser> elements() {
        return controller.listUsers();
    }

    @Override
    protected Visitor<CafeteriaUser> elementPrinter() {
        return new CafeteriaUserPrinter();
    }

    @Override
    protected String elementName() {
        return "User";
    }

    @Override
    protected String listHeader() {
        return String.format(
                "#  %-35s%-40s%-15s",
                "EMAIL", "NAME", "STATUS"
        ) + "\n";
    }

}
