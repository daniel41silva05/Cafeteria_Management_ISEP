package lapr4.cafeteria.meal_booking.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.validations.Preconditions;
import lapr4.cafeteria.infrastructure.persistence.PersistenceContext;
import lapr4.cafeteria.meal_booking.domain.Booking;
import lapr4.cafeteria.meal_booking.domain.BookingToken;
import lapr4.cafeteria.meal_booking.repositories.BookingRepository;
import lapr4.cafeteria.user_management.domain.CafeteriaRoles;

@UseCaseController
public class MarkBookingAsDeliveredController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final BookingRepository repo = PersistenceContext.repositories().bookings();

    public Booking bookingByToken(final String bookingToken) {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.CASHIER);

        return repo.ofIdentity(BookingToken.valueOf(bookingToken))
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
    }

    public Booking markBookingAsDelivered(final Booking booking) {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.CASHIER);
        Preconditions.nonNull(booking);

        booking.deliver();
        return repo.save(booking);
    }

}
