package lapr4.cafeteria.infrastructure.persistence;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventConsumptionRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventRecordRepository;
import lapr4.cafeteria.card_account.repositories.CardBalanceRepository;
import lapr4.cafeteria.card_account.repositories.CardMovementRepository;
import lapr4.cafeteria.dish_management.repositories.DishRepository;
import lapr4.cafeteria.meal_booking.repositories.BookingRepository;
import lapr4.cafeteria.meal_management.repositories.MealRepository;
import lapr4.cafeteria.user_management.repositories.CafeteriaUserRepository;

public interface RepositoryFactory {

    TransactionalContext newTransactionalContext();

    UserRepository users(TransactionalContext autoTx);

    UserRepository users();

    CafeteriaUserRepository cafeteriaUsers();

    DishRepository dishes();

    MealRepository meals();

    CardBalanceRepository cardsBalance(TransactionalContext autoTx);

    CardBalanceRepository cardsBalance();

    CardMovementRepository cardMovements(TransactionalContext autoTx);

    CardMovementRepository cardMovements();

    BookingRepository bookings();

    BookingRepository bookings(TransactionalContext autoTx);

    EventConsumptionRepository eventConsumption();

    EventRecordRepository eventRecord();

}
