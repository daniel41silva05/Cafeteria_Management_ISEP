package lapr4.cafeteria.user_management.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import lapr4.cafeteria.user_management.domain.CafeteriaRoles;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;

@UseCaseController
public class ListUsersController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CafeteriaUserService svc = new CafeteriaUserService();

    public Iterable<CafeteriaUser> listUsers() {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.ADMIN);
        return svc.allUsers();
    }

}
