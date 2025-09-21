package lapr4.cafeteria.meal_booking.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import lapr4.cafeteria.meal_booking.domain.Booking;
import lapr4.cafeteria.user_management.application.CafeteriaUserService;
import lapr4.cafeteria.user_management.domain.CafeteriaRoles;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;

import java.util.Calendar;
import java.util.Optional;

@UseCaseController
public class ConsultMyBookingController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListBookingService svc = new ListBookingService();
    private final CafeteriaUserService userSvc = new CafeteriaUserService();

    public Iterable<Booking> bookingsByUserForDay(final Calendar forDay) {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.CAFETERIA_USER);
        return svc.allBookingByUserForDay(currentUser(), forDay);
    }

    public Iterable<Booking> bookingsByUserForPeriod(final Calendar beginDate, final Calendar endDate) {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.CAFETERIA_USER);
        return svc.allBookingByUserForPeriod(currentUser(), beginDate, endDate);
    }

    private CafeteriaUser currentUser() {
        Optional<CafeteriaUser> user = authz.session().flatMap(s -> userSvc.findUserByUsername(s.authenticatedUser().username()));
        return user.orElseThrow(IllegalStateException::new);
    }

}