package lapr4.cafeteria.app.backoffice.console.presentation.users;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import lapr4.cafeteria.user_management.application.AddUserController;
import lapr4.cafeteria.user_management.domain.CafeteriaRoles;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("java:S106")
public class AddUserUI extends AbstractUI {

    private final AddUserController controller = new AddUserController();

    @Override
    protected boolean doShow() {
        final String mecanographicNumber = Console.readLine("Mecanographic Number");
        final String email = Console.readLine("E-Mail");
        final String password = Console.readLine("Password");
        final String firstName = Console.readLine("First Name");
        final String lastName = Console.readLine("Last Name");

        final Set<Role> roleTypes = new HashSet<>();
        boolean show;
        do {
            show = showRoles(roleTypes);
        } while (!show);

        try {
            CafeteriaUser user = controller.addUser(mecanographicNumber, email, password, firstName, lastName, roleTypes);
            showUserResult(user);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("That mecanographic number is already in use.");
        }

        return false;
    }

    private boolean showRoles(final Set<Role> roleTypes) {
        final Menu rolesMenu = buildRolesMenu(roleTypes);
        final MenuRenderer renderer = new VerticalMenuRenderer(rolesMenu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildRolesMenu(final Set<Role> roleTypes) {
        final Menu rolesMenu = new Menu();
        int counter = 0;
        rolesMenu.addItem(MenuItem.of(counter++, "No Role", Actions.SUCCESS));
        for (final Role roleType : CafeteriaRoles.allRoles()) {
            rolesMenu.addItem(
                    MenuItem.of(counter++, roleType.toString(), () -> roleTypes.add(roleType)));
        }
        return rolesMenu;
    }

    private void showUserResult(final CafeteriaUser user) {
        System.out.println("\n=== Registration Successful ===");

        System.out.println("Mecanographic Number: " + user.identity().toString());
        System.out.println("Email: " + user.user().email().toString());
        System.out.println("Name: " + user.user().name().toString());
    }

    @Override
    public String headline() {
        return "Add User";
    }
}
