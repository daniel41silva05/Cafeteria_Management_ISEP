package lapr4.cafeteria.meal_booking.application;

import eapli.framework.application.ApplicationService;
import lapr4.cafeteria.infrastructure.persistence.PersistenceContext;
import lapr4.cafeteria.meal_booking.domain.Booking;
import lapr4.cafeteria.meal_booking.repositories.BookingRepository;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;

import java.util.Calendar;

@ApplicationService
public class ListBookingService {

    private final BookingRepository repo = PersistenceContext.repositories().bookings();

    public Iterable<Booking> allBookingForDay(final Calendar forDay) {
        return repo.findAllForDay(forDay);
    }

    public Iterable<Booking> allBookingByUserForDay(final CafeteriaUser user, final Calendar forDay) {
        return repo.findByUserForDay(user, forDay);
    }

    public Iterable<Booking> allBookingByUserForPeriod(final CafeteriaUser user, final Calendar beginDate, final Calendar endDate) {
        return repo.findByUserForPeriod(user, beginDate, endDate);
    }

}
