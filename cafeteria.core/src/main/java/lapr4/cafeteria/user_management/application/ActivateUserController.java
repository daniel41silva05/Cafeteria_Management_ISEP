package lapr4.cafeteria.user_management.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;
import lapr4.cafeteria.infrastructure.persistence.PersistenceContext;
import lapr4.cafeteria.user_management.domain.CafeteriaRoles;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;
import lapr4.cafeteria.user_management.repositories.CafeteriaUserRepository;

@UseCaseController
public class ActivateUserController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService svc = AuthzRegistry.userService();
    private final CafeteriaUserRepository repo = PersistenceContext.repositories().cafeteriaUsers();

    public Iterable<CafeteriaUser> disabledUsers() {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.ADMIN);
        return repo.findAllDisabled();
    }

    public SystemUser enableUser(final CafeteriaUser cafeteriaUser) {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.ADMIN);
        Preconditions.nonNull(cafeteriaUser);

        return svc.activateUser(cafeteriaUser.user());
    }

}
