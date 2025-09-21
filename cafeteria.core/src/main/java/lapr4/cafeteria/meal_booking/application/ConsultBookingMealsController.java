package lapr4.cafeteria.meal_booking.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import lapr4.cafeteria.meal_booking.domain.Booking;
import lapr4.cafeteria.meal_management.domain.Meal;
import lapr4.cafeteria.user_management.domain.CafeteriaRoles;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@UseCaseController
public class ConsultBookingMealsController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListBookingService svc = new ListBookingService();

    public Map<Meal, Integer> bookingsPerMealOnDay(final Calendar forDay) {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.KITCHEN_MANAGER);

        Map<Meal, Integer> bookingsPerMeal = new HashMap<>();

        for (Booking booking : svc.allBookingForDay(forDay)) {
            Meal meal = booking.meal();
            bookingsPerMeal.put(meal, bookingsPerMeal.getOrDefault(meal, 0) + 1);
        }

        return bookingsPerMeal;
    }

}