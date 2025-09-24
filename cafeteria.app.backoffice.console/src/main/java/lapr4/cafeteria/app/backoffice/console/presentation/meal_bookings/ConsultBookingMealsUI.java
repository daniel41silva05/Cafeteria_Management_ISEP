package lapr4.cafeteria.app.backoffice.console.presentation.meal_bookings;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import lapr4.cafeteria.meal_booking.application.ConsultBookingMealsController;
import lapr4.cafeteria.meal_management.domain.Meal;

import java.util.Calendar;
import java.util.Map;

@SuppressWarnings("java:S106")
public class ConsultBookingMealsUI extends AbstractUI {

    private final ConsultBookingMealsController controller = new ConsultBookingMealsController();

    @Override
    protected boolean doShow() {
        final Calendar day = Console.readCalendar("Day: ");

        final Map<Meal, Integer> bookingsPerMeal = controller.bookingsPerMealOnDay(day);

        if (bookingsPerMeal.isEmpty()) {
            System.out.println("There are no bookings for that day.");
        } else {
            System.out.println("\n=== Bookings for " + day.getTime() + " ===\n");
            System.out.printf("%-15s %-20s %-10s%n", "MEAL TYPE", "DISH NAME", "BOOKINGS");

            for (Map.Entry<Meal, Integer> entry : bookingsPerMeal.entrySet()) {
                final Meal meal = entry.getKey();
                final int count = entry.getValue();

                System.out.printf(
                        "%-15s %-20s %-10d%n",
                        meal.mealType(),
                        meal.dish().name(),
                        count
                );
            }
        }

        return false;
    }

    @Override
    public String headline() {
        return "Consult Bookings per Meal";
    }
}
