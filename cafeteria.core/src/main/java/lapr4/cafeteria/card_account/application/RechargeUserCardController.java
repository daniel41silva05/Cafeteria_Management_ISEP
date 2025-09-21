package lapr4.cafeteria.card_account.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.money.domain.model.Money;
import lapr4.cafeteria.card_account.domain.CardBalance;
import lapr4.cafeteria.card_account.domain.CardMovement;
import lapr4.cafeteria.card_account.domain.MovementType;
import lapr4.cafeteria.card_account.repositories.CardBalanceRepository;
import lapr4.cafeteria.card_account.repositories.CardMovementRepository;
import lapr4.cafeteria.infrastructure.persistence.PersistenceContext;
import lapr4.cafeteria.user_management.application.CafeteriaUserService;
import lapr4.cafeteria.user_management.domain.CafeteriaRoles;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;

import java.util.Optional;

@UseCaseController
public class RechargeUserCardController {

    private final CafeteriaUserService userSvc = new CafeteriaUserService();
    private final CardBalanceService balanceSvc = new CardBalanceService();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TransactionalContext ctx = PersistenceContext.repositories().newTransactionalContext();
    private final CardBalanceRepository balanceRepo = PersistenceContext.repositories().cardsBalance(ctx);
    private final CardMovementRepository movementRepo = PersistenceContext.repositories().cardMovements(ctx);

    // POS movement
    public CardMovement rechargeUserCard(final String mecanographicNumber, final double amount) {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.CASHIER);

        ctx.beginTransaction();

        CafeteriaUser user = userSvc.findUserByMecNumber(mecanographicNumber)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found with mecanographic number: " + mecanographicNumber));

        Money value = Money.euros(amount);

        CardMovement movement = new CardMovement(MovementType.RECHARGE, value, user);
        movement = movementRepo.save(movement);

        Optional<CardBalance> balanceOpt = balanceSvc.findCardBalanceByMecNumber(mecanographicNumber);
        CardBalance balance;
        if (balanceOpt.isEmpty()) {
            balance = new CardBalance(user, value);
        } else {
            balance = balanceOpt.get();
            balance.applyMovement(value);
        }
        balanceRepo.save(balance);

        ctx.commit();

        return movement;
    }

}
