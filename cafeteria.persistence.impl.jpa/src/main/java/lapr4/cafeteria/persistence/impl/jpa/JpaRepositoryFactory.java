package lapr4.cafeteria.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.jpa.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventConsumptionRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventRecordRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.jpa.JpaAutoTxEventConsumptionRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.jpa.JpaAutoTxEventRecordRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import lapr4.cafeteria.card_account.repositories.CardBalanceRepository;
import lapr4.cafeteria.card_account.repositories.CardMovementRepository;
import lapr4.cafeteria.dish_management.repositories.DishRepository;
import lapr4.cafeteria.infrastructure.Application;
import lapr4.cafeteria.infrastructure.persistence.RepositoryFactory;
import lapr4.cafeteria.meal_booking.repositories.BookingRepository;
import lapr4.cafeteria.meal_management.repositories.MealRepository;
import lapr4.cafeteria.user_management.repositories.CafeteriaUserRepository;

public class JpaRepositoryFactory implements RepositoryFactory {

	@Override
	public UserRepository users(final TransactionalContext autoTx) {
		return new JpaAutoTxUserRepository(autoTx);
	}

	@Override
	public UserRepository users() {
		return new JpaAutoTxUserRepository(Application.settings().persistenceUnitName(),
				Application.settings().extendedPersistenceProperties());
	}

	@Override
	public CafeteriaUserRepository cafeteriaUsers() {
		return new JpaCafeteriaUserRepository();
	}

	@Override
	public DishRepository dishes() {
		return new JpaDishRepository();
	}

	@Override
	public MealRepository meals() {
		return new JpaMealRepository();
	}

	@Override
	public CardBalanceRepository cardsBalance(final TransactionalContext autoTx) {
		return new JpaCardBalanceRepository(autoTx);
	}

	@Override
	public CardBalanceRepository cardsBalance() {
		return new JpaCardBalanceRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public CardMovementRepository cardMovements(final TransactionalContext autoTx) {
		return new JpaCardMovementRepository(autoTx);
	}

	@Override
	public CardMovementRepository cardMovements() {
		return new JpaCardMovementRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public BookingRepository bookings(final TransactionalContext autoTx) {
		return new JpaBookingRepository(autoTx);
	}

	@Override
	public BookingRepository bookings() {
		return new JpaBookingRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public TransactionalContext newTransactionalContext() {
		return JpaAutoTxRepository.buildTransactionalContext(Application.settings().persistenceUnitName(),
				Application.settings().extendedPersistenceProperties());
	}

	@Override
	public EventConsumptionRepository eventConsumption() {
		return new JpaAutoTxEventConsumptionRepository(Application.settings().persistenceUnitName(),
				Application.settings().extendedPersistenceProperties());
	}

	@Override
	public EventRecordRepository eventRecord() {
		return new JpaAutoTxEventRecordRepository(Application.settings().persistenceUnitName(),
				Application.settings().extendedPersistenceProperties());
	}

}
