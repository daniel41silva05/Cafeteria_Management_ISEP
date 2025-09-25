package lapr4.cafeteria.app.backoffice.console.presentation.meal_bookings;

import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import lapr4.cafeteria.app.backoffice.console.presentation.meals.MealPrinter;
import lapr4.cafeteria.meal_booking.application.BookMealController;
import lapr4.cafeteria.meal_booking.domain.Booking;
import lapr4.cafeteria.meal_management.domain.Meal;
import lapr4.cafeteria.meal_management.domain.MealType;

import java.util.Calendar;

@SuppressWarnings("java:S106")
public class BookMealUI extends AbstractUI {

    private final BookMealController controller = new BookMealController();

    @Override
    protected boolean doShow() {

        final MealType mealType = selectMealType();
        final Calendar day = readValidDate();

        final Iterable<Meal> meals = controller.mealsForDayAndType(day, mealType);

        if (!meals.iterator().hasNext()) {
            System.out.println("There are no meals");
        } else {
            MealPrinter printer = new MealPrinter();
            final SelectWidget<Meal> selector = new SelectWidget<>(printer.header(), meals, printer);
            selector.show();
            Meal selectedMeal = selector.selectedElement();

            if (selectedMeal != null) {
                try {
                    Booking booking = controller.bookMeal(selectedMeal);
                    showBookingResult(booking);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
                    System.out.println("That id is already in use.");
                }
            }
        }

        return false;
    }

    private MealType selectMealType() {
        System.out.println("Meal Types Available:");
        for (final MealType type : MealType.values()) {
            System.out.println("\t" + type.toString());
        }

        do {
            try {
                final String type = Console.readLine("Meal Type: ").toUpperCase();
                return MealType.valueOf(type);
            } catch (final IllegalArgumentException e) {
                System.out.println("Please try again. Enter a valid meal type.");
            }
        } while (true);
    }

    private void showBookingResult(final Booking booking) {
        System.out.println("\n=== Registration Successful ===");

        System.out.println("Booking Token: " + booking.identity());
        System.out.println("Status: " + booking.status());
        System.out.println("Cost: " + booking.cost());
        System.out.println("Date: " + booking.meal().ofDay().getTime());
        System.out.println("Meal Type: " + booking.meal().mealType());
        System.out.println("Dish Name: " + booking.meal().dish().name());
        System.out.println("Dish Type: " + booking.meal().dish().dishType());
    }

    private Calendar readValidDate() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        Calendar chosenDate;
        do {
            chosenDate = Console.readCalendar("Day (dd-mm-yyyy): ");
            if (chosenDate.before(today)) {
                System.out.println("The date cannot be in the past. Please try again.");
            }
        } while (chosenDate.before(today));

        return chosenDate;
    }

    @Override
    public String headline() {
        return "Book a Meal";
    }

}
