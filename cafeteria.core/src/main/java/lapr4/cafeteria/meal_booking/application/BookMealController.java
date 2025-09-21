package lapr4.cafeteria.meal_booking.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.money.domain.model.Money;
import lapr4.cafeteria.card_account.application.CardBalanceService;
import lapr4.cafeteria.card_account.domain.CardBalance;
import lapr4.cafeteria.card_account.domain.CardMovement;
import lapr4.cafeteria.card_account.domain.MovementType;
import lapr4.cafeteria.card_account.repositories.CardBalanceRepository;
import lapr4.cafeteria.card_account.repositories.CardMovementRepository;
import lapr4.cafeteria.infrastructure.persistence.PersistenceContext;
import lapr4.cafeteria.meal_booking.domain.Booking;
import lapr4.cafeteria.meal_booking.repositories.BookingRepository;
import lapr4.cafeteria.meal_management.application.ListMealService;
import lapr4.cafeteria.meal_management.domain.Meal;
import lapr4.cafeteria.meal_management.domain.MealType;
import lapr4.cafeteria.user_management.application.CafeteriaUserService;
import lapr4.cafeteria.user_management.domain.CafeteriaRoles;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;

import java.util.Calendar;
import java.util.Optional;

@UseCaseController
public class BookMealController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListMealService mealSvc = new ListMealService();
    private final CafeteriaUserService userSvc = new CafeteriaUserService();
    private final CardBalanceService balanceSvc = new CardBalanceService();
    private final TransactionalContext ctx = PersistenceContext.repositories().newTransactionalContext();
    private final CardMovementRepository movementRepo = PersistenceContext.repositories().cardMovements(ctx);
    private final CardBalanceRepository balanceRepo = PersistenceContext.repositories().cardsBalance(ctx);
    private final BookingRepository bookingRepo = PersistenceContext.repositories().bookings(ctx);

    public Iterable<Meal> mealsForDayAndType(final Calendar forDay, final MealType mealType) {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.CAFETERIA_USER);
        return mealSvc.allMealsForDayAndType(forDay, mealType);
    }

    public Booking bookMeal(final Meal meal) {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.CAFETERIA_USER);

        ctx.beginTransaction();

        Money cost = meal.dish().price();
        CafeteriaUser user = currentUser();

        CardBalance cardBalance = balanceSvc.findCardBalanceByMecNumber(user.identity())
                .orElseThrow(this::insufficientBalance);

        if (!cardBalance.balance().isGreaterThanOrEqual(cost)) {
            throw insufficientBalance();
        }

        cardBalance.applyMovement(cost.negate());
        balanceRepo.save(cardBalance);

        CardMovement movement = new CardMovement(MovementType.PURCHASE, cost, user);
        movementRepo.save(movement);

        Booking booking = bookingRepo.save(new Booking(user, meal));

        ctx.commit();
        return booking;
    }

    private CafeteriaUser currentUser() {
        Optional<CafeteriaUser> user = authz.session().flatMap(s -> userSvc.findUserByUsername(s.authenticatedUser().username()));
        return user.orElseThrow(IllegalStateException::new);
    }

    private IllegalArgumentException insufficientBalance() {
        ctx.rollback();
        return new IllegalArgumentException("Insufficient balance to book meal.");
    }

}
