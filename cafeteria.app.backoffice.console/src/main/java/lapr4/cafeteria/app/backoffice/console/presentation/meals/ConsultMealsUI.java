package lapr4.cafeteria.app.backoffice.console.presentation.meals;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import lapr4.cafeteria.meal_management.application.ConsultMealController;
import lapr4.cafeteria.meal_management.domain.Meal;
import lapr4.cafeteria.meal_management.domain.MealType;

import java.util.Calendar;

@SuppressWarnings("java:S106")
public class ConsultMealsUI extends AbstractUI {

    private final ConsultMealController controller = new ConsultMealController();

    @Override
    protected boolean doShow() {
        System.out.println("====== Meal Menu ======");
        System.out.println("1 - List meal for day");
        System.out.println("2 - List meal for period");
        System.out.println("3 - List meal for day and type");
        System.out.println("0 - Exit");

        int choice = Console.readOption(1, 3, 0);

        switch (choice) {
            case 1:
                final Calendar day1 = Console.readCalendar("Day: ");
                showList(controller.mealsForDay(day1));
                break;

            case 2:
                final Calendar beginDay = Console.readCalendar("Begin Day: ");
                final Calendar endDay = Console.readCalendar("End Day: ");
                showList(controller.mealsForPeriod(beginDay, endDay));
                break;

            case 3:
                final Calendar day2 = Console.readCalendar("Day: ");
                final MealType type = selectMealType();
                showList(controller.mealsForDayAndType(day2, type));
                break;

            case 0:
                System.out.println("Exiting...");
                break;
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

    private void showList(final Iterable<Meal> meals) {
        if (!meals.iterator().hasNext()) {
            System.out.println("There are no available meals");
            return;
        }
        MealPrinter printer = new MealPrinter();
        System.out.println(printer.header());
        for (Meal meal : meals) {
            printer.visit(meal);
        }
    }

    @Override
    public String headline() {
        return "Consult Meals";
    }

}
