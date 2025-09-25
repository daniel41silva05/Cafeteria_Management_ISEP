package lapr4.cafeteria.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import lapr4.cafeteria.meal_booking.domain.Booking;
import lapr4.cafeteria.meal_booking.domain.BookingToken;
import lapr4.cafeteria.meal_booking.repositories.BookingRepository;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;

import java.util.Calendar;

public class InMemoryBookingRepository extends InMemoryDomainRepository<Booking, BookingToken>
        implements BookingRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Booking> findAllForDay(Calendar forDay) {
        return match(booking -> sameDay(booking.meal().ofDay(), forDay));
    }

    @Override
    public Iterable<Booking> findByUserForDay(CafeteriaUser user, Calendar forDay) {
        return match(booking ->
                booking.user().equals(user) &&
                        sameDay(booking.meal().ofDay(), forDay));
    }

    @Override
    public Iterable<Booking> findByUserForPeriod(CafeteriaUser user, Calendar beginDate, Calendar endDate) {
        return match(booking ->
                booking.user().equals(user) &&
                        !booking.meal().ofDay().before(beginDate) &&
                        !booking.meal().ofDay().after(endDate));
    }

    private boolean sameDay(Calendar c1, Calendar c2) {
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
    }
}
