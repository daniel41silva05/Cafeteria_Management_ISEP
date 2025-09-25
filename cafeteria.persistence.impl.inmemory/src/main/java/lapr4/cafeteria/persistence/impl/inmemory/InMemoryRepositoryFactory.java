package lapr4.cafeteria.persistence.impl.inmemory;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventConsumptionRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventRecordRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryTransactionalContext;
import lapr4.cafeteria.card_account.repositories.CardBalanceRepository;
import lapr4.cafeteria.card_account.repositories.CardMovementRepository;
import lapr4.cafeteria.dish_management.repositories.DishRepository;
import lapr4.cafeteria.infrastructure.bootstrapers.CafeteriaBootstrapper;
import lapr4.cafeteria.infrastructure.persistence.RepositoryFactory;
import lapr4.cafeteria.meal_booking.repositories.BookingRepository;
import lapr4.cafeteria.meal_management.repositories.MealRepository;
import lapr4.cafeteria.user_management.repositories.CafeteriaUserRepository;

public class InMemoryRepositoryFactory implements RepositoryFactory {

	@Override
	public TransactionalContext newTransactionalContext() {
		return new InMemoryTransactionalContext();
	}

	@Override
	public UserRepository users(final TransactionalContext tx) {
		final var repo = new InMemoryUserRepository();
		CafeteriaBootstrapper.registerPowerUser(repo);
		return repo;
	}

	@Override
	public UserRepository users() {
		return users(null);
	}

	@Override
	public CafeteriaUserRepository cafeteriaUsers() {
		return new InMemoryCafeteriaUserRepository();
	}

	@Override
	public DishRepository dishes() {
		return new InMemoryDishRepository();
	}

	@Override
	public MealRepository meals() {
		return new InMemoryMealRepository();
	}

	@Override
	public CardBalanceRepository cardsBalance() {
		return cardsBalance(null);
	}

	@Override
	public CardBalanceRepository cardsBalance(final TransactionalContext tx) {
		return new InMemoryCardBalanceRepository();
	}

	@Override
	public CardMovementRepository cardMovements() {
		return cardMovements(null);
	}

	@Override
	public CardMovementRepository cardMovements(final TransactionalContext tx) {
		return new InMemoryCardMovementRepository();
	}

	@Override
	public BookingRepository bookings() {
		return bookings(null);
	}

	@Override
	public BookingRepository bookings(final TransactionalContext tx) {
		return new InMemoryBookingRepository();
	}

	@Override
	public EventConsumptionRepository eventConsumption() {
		throw new IllegalStateException("Not implemented yet.");
	}

	@Override
	public EventRecordRepository eventRecord() {
		throw new IllegalStateException("Not implemented yet.");
	}

}
