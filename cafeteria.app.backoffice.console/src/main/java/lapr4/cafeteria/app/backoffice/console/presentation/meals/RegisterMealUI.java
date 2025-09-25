package lapr4.cafeteria.app.backoffice.console.presentation.meals;

import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import lapr4.cafeteria.app.backoffice.console.presentation.dishes.DishPrinter;
import lapr4.cafeteria.dish_management.domain.Dish;
import lapr4.cafeteria.meal_management.application.RegisterMealController;
import lapr4.cafeteria.meal_management.domain.Meal;
import lapr4.cafeteria.meal_management.domain.MealType;

import java.util.Calendar;

@SuppressWarnings("java:S106")
public class RegisterMealUI extends AbstractUI {

    private final RegisterMealController controller = new RegisterMealController();

    @Override
    protected boolean doShow() {
        final Iterable<Dish> dishes = controller.listDish();

        if (!dishes.iterator().hasNext()) {
            System.out.println("There are no dishes");
        } else {
            DishPrinter printer = new DishPrinter();
            final SelectWidget<Dish> selector = new SelectWidget<>(printer.header(), dishes, printer);
            selector.show();
            Dish selectedDish = selector.selectedElement();

            if (selectedDish != null) {
                final MealType mealType = selectMealType();
                final Calendar day = readValidDate();

                try {
                    Meal meal = controller.registerMeal(selectedDish, mealType, day);
                    showMealResult(meal);
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

    private void showMealResult(final Meal meal) {
        System.out.println("\n=== Registration Successful ===");

        System.out.println("Meal Type: " + meal.mealType());
        System.out.println("Date: " + meal.ofDay().getTime());
        System.out.println("Dish Name: " + meal.dish().name());
        System.out.println("Dish Type: " + meal.dish().dishType());
        System.out.println("Price: " + meal.dish().price());
        System.out.println("Short Description: " + meal.dish().shortDescription());
        System.out.println("Calories: " + meal.dish().nutricionalInfo().calories());
        System.out.println("Salt: " + meal.dish().nutricionalInfo().salt());
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
        return "Register Meal";
    }

}
