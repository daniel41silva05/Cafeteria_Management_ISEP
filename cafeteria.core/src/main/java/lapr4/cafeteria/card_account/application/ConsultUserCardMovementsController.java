package lapr4.cafeteria.card_account.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import lapr4.cafeteria.card_account.domain.CardMovement;
import lapr4.cafeteria.card_account.repositories.CardMovementRepository;
import lapr4.cafeteria.infrastructure.persistence.PersistenceContext;
import lapr4.cafeteria.user_management.application.CafeteriaUserService;
import lapr4.cafeteria.user_management.domain.CafeteriaRoles;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;

import java.util.Calendar;
import java.util.Optional;

@UseCaseController
public class ConsultUserCardMovementsController {

    private final CafeteriaUserService userSvc = new CafeteriaUserService();
    private final CardMovementRepository repo = PersistenceContext.repositories().cardMovements();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public Iterable<CardMovement> consultAllCardMovements() {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.CAFETERIA_USER);

        CafeteriaUser user = currentUser();

        return repo.findByMecanographicNumber(user.identity());
    }

    public Iterable<CardMovement> consultCardMovementsOnPeriod(final Calendar beginDate, final Calendar endDate) {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.CAFETERIA_USER);

        CafeteriaUser user = currentUser();

        return repo.findByMecanographicNumberAndPeriod(user.identity(), beginDate, endDate);
    }

    private CafeteriaUser currentUser() {
        Optional<CafeteriaUser> user = authz.session().flatMap(s -> userSvc.findUserByUsername(s.authenticatedUser().username()));
        return user.orElseThrow(IllegalStateException::new);
    }

}
