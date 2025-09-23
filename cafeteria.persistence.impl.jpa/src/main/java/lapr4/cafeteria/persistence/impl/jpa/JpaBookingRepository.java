package lapr4.cafeteria.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import lapr4.cafeteria.infrastructure.Application;
import lapr4.cafeteria.meal_booking.domain.Booking;
import lapr4.cafeteria.meal_booking.domain.BookingToken;
import lapr4.cafeteria.meal_booking.repositories.BookingRepository;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;

import java.util.Calendar;

class JpaBookingRepository extends JpaAutoTxRepository<Booking, Long, BookingToken>
        implements BookingRepository {

    public JpaBookingRepository(final TransactionalContext autoTx) {
        super(autoTx, "token");
    }

    public JpaBookingRepository(final String puname) {
        super(puname, Application.settings().extendedPersistenceProperties(), "token");
    }

    @Override
    public Iterable<Booking> findAllForDay(final Calendar forDay) {
        final var query = entityManager().createQuery(
                "SELECT b FROM Booking b " +
                        "WHERE b.meal.ofDay = :day " +
                        "ORDER BY b.meal.mealType ASC",
                Booking.class
        );
        query.setParameter("day", forDay);
        return query.getResultList();
    }

    @Override
    public Iterable<Booking> findByUserForDay(final CafeteriaUser user, final Calendar forDay) {
        final var query = entityManager().createQuery(
                "SELECT b FROM Booking b " +
                        "WHERE b.user = :user " +
                        "AND b.meal.ofDay = :day " +
                        "ORDER BY b.meal.mealType ASC",
                Booking.class
        );
        query.setParameter("user", user);
        query.setParameter("day", forDay);
        return query.getResultList();
    }

    @Override
    public Iterable<Booking> findByUserForPeriod(final CafeteriaUser user,
                                                 final Calendar beginDate,
                                                 final Calendar endDate) {
        final var query = entityManager().createQuery(
                "SELECT b FROM Booking b " +
                        "WHERE b.user = :user " +
                        "AND b.meal.ofDay BETWEEN :begin AND :end " +
                        "ORDER BY b.meal.ofDay ASC, b.meal.mealType ASC",
                Booking.class
        );
        query.setParameter("user", user);
        query.setParameter("begin", beginDate);
        query.setParameter("end", endDate);
        return query.getResultList();
    }

}
