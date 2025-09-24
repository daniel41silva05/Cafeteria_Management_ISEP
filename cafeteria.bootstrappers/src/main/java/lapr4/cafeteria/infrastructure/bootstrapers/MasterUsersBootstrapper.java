package lapr4.cafeteria.infrastructure.bootstrapers;

import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;
import lapr4.cafeteria.user_management.domain.CafeteriaRoles;

import java.util.HashSet;
import java.util.Set;

public class MasterUsersBootstrapper extends UsersBootstrapperBase implements Action {

    @Override
    public boolean execute() {
        registerAdmin("1231046", "jane.doe@showdrone.com", TestDataConstants.PASSWORD1, "Jane", "Doe Admin");
        return true;
    }

    private void registerAdmin(final String mecanographicNumber, final String email, final String password,
                               final String firstName, final String lastName) {
        final Set<Role> roles = new HashSet<>();
        roles.add(CafeteriaRoles.ADMIN);

        registerUser(mecanographicNumber, email, password, firstName, lastName, roles);
    }
}
