package lapr4.cafeteria.card_account.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import lapr4.cafeteria.user_management.application.CafeteriaUserService;
import lapr4.cafeteria.user_management.domain.CafeteriaRoles;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;

import java.math.BigDecimal;
import java.util.Optional;

@UseCaseController
public class ConsultUserCardBalanceController {

    private final CafeteriaUserService userSvc = new CafeteriaUserService();
    private final CardBalanceService balanceSvc = new CardBalanceService();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public BigDecimal consultCardBalance() {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.CAFETERIA_USER);

        CafeteriaUser user = currentUser();

        return balanceSvc.findCardBalanceByMecNumber(user.identity())
                .map(b -> b.balance().amount())
                .orElse(BigDecimal.ZERO);
    }

    private CafeteriaUser currentUser() {
        Optional<CafeteriaUser> user = authz.session().flatMap(s -> userSvc.findUserByUsername(s.authenticatedUser().username()));
        return user.orElseThrow(IllegalStateException::new);
    }

}
