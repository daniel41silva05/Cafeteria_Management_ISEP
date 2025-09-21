package lapr4.cafeteria.meal_booking.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import lapr4.cafeteria.meal_booking.domain.Booking;
import lapr4.cafeteria.meal_booking.domain.BookingToken;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;

import java.util.Calendar;

public interface BookingRepository extends DomainRepository<BookingToken, Booking> {

    Iterable<Booking> findAllForDay(Calendar forDay);

    Iterable<Booking> findByUserForDay(CafeteriaUser user, Calendar forDay);

    Iterable<Booking> findByUserForPeriod(CafeteriaUser user, Calendar beginDate, Calendar endDate);

}
