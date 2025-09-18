package lapr4.cafeteria.app.backoffice.console.presentation.users;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import lapr4.cafeteria.user_management.application.DeactivateUserController;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;

@SuppressWarnings("squid:S106")
public class DeactivateUserUI extends AbstractUI {

    private final DeactivateUserController controller = new DeactivateUserController();

    @Override
    protected boolean doShow() {
        final Iterable<CafeteriaUser> activeUsers = controller.activeUsers();

        if (!activeUsers.iterator().hasNext()) {
            System.out.println("There are no active users");
        } else {
            CafeteriaUserPrinter printer = new CafeteriaUserPrinter();
            final SelectWidget<CafeteriaUser> selector = new SelectWidget<>(printer.header(), activeUsers,
                    printer);
            selector.show();
            final CafeteriaUser selectedUser = selector.selectedElement();
            if (selectedUser != null) {
                try {
                    SystemUser user = controller.disableUser(selectedUser);
                    showUserResult(user);
                } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                    System.out.println(
                            "WARNING: That entity has already been changed or deleted since you last read it");
                }
            }
        }

        return false;
    }

    private void showUserResult(final SystemUser user) {
        System.out.printf(
                "%-35s%-20s%-20s%-15s%n",
                "NUMBER", "FIRST NAME", "LAST NAME", "STATUS"
        );
        String status = user.isActive() ? "Active" : "Inactive";
        System.out.printf(
                "%-35s%-20s%-20s%-15s%n",
                user.username(),
                user.name().firstName(),
                user.name().lastName(),
                status
        );
    }

    @Override
    public String headline() {
        return "Disable User";
    }
}
