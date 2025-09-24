package lapr4.cafeteria.infrastructure.bootstrapers.demo;

import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;
import lapr4.cafeteria.infrastructure.bootstrapers.TestDataConstants;
import lapr4.cafeteria.infrastructure.bootstrapers.UsersBootstrapperBase;
import lapr4.cafeteria.user_management.domain.CafeteriaRoles;

import java.util.HashSet;
import java.util.Set;

public class CafeteriaUsersBootstrapper extends UsersBootstrapperBase implements Action {

    @Override
    public boolean execute() {
        registerKitchenManager("111222", "kitchen123@cafeteria.com", TestDataConstants.PASSWORD1, "Kitchen", "Manager");
        registerMenuManager("222111", "manager123@cafeteria.com", TestDataConstants.PASSWORD1, "Menu", "Manager");
        registerCashier("111333", "cashier123@cafeteria.com", TestDataConstants.PASSWORD1, "João", "Cashier");
        return true;
    }

    private void registerKitchenManager(final String mecanographicNumber, final String email, final String password,
                                        final String firstName, final String lastName) {
        final Set<Role> roles = new HashSet<>();
        roles.add(CafeteriaRoles.KITCHEN_MANAGER);

        registerUser(mecanographicNumber, email, password, firstName, lastName, roles);
    }

    private void registerMenuManager(final String mecanographicNumber, final String email, final String password,
                                     final String firstName, final String lastName) {
        final Set<Role> roles = new HashSet<>();
        roles.add(CafeteriaRoles.MENU_MANAGER);

        registerUser(mecanographicNumber, email, password, firstName, lastName, roles);
    }

    private void registerCashier(final String mecanographicNumber, final String email, final String password,
                                 final String firstName, final String lastName) {
        final Set<Role> roles = new HashSet<>();
        roles.add(CafeteriaRoles.CASHIER);

        registerUser(mecanographicNumber, email, password, firstName, lastName, roles);
    }

}
