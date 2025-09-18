package lapr4.cafeteria.user_management.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.util.CurrentTimeCalendars;
import lapr4.cafeteria.infrastructure.persistence.PersistenceContext;
import lapr4.cafeteria.user_management.domain.CafeteriaRoles;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;
import lapr4.cafeteria.user_management.domain.MecanographicNumber;
import lapr4.cafeteria.user_management.repositories.CafeteriaUserRepository;

import java.util.Set;

@UseCaseController
public class AddUserController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService svc = AuthzRegistry.userService();
    private final CafeteriaUserRepository repo = PersistenceContext.repositories().cafeteriaUsers();

    public CafeteriaUser addUser(final String mecanographicNumber, final String email, final String password,
                                 final String firstName, final String lastName, final Set<Role> roles) {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.ADMIN);

        SystemUser systemUser = svc.registerNewUser(mecanographicNumber, password, firstName,
                lastName, email, roles, CurrentTimeCalendars.now());

        CafeteriaUser newUser = new CafeteriaUser(systemUser, MecanographicNumber.valueOf(mecanographicNumber));

        return repo.save(newUser);
    }

}
