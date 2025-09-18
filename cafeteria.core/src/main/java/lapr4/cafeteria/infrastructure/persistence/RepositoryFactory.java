package lapr4.cafeteria.infrastructure.persistence;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventConsumptionRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventRecordRepository;
import lapr4.cafeteria.dish_management.repositories.DishRepository;
import lapr4.cafeteria.meal_management.repositories.MealRepository;
import lapr4.cafeteria.user_management.repositories.CafeteriaUserRepository;

public interface RepositoryFactory {

    TransactionalContext newTransactionalContext();

    UserRepository users(TransactionalContext autoTx);

    UserRepository users();

    CafeteriaUserRepository cafeteriaUsers(TransactionalContext autoTx);

    CafeteriaUserRepository cafeteriaUsers();

    DishRepository dishes();

    MealRepository meals();

    EventConsumptionRepository eventConsumption();

    EventRecordRepository eventRecord();

}
