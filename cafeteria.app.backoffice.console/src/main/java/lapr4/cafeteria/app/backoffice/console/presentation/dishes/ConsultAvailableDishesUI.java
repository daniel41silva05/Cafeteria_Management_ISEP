package lapr4.cafeteria.app.backoffice.console.presentation.dishes;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import lapr4.cafeteria.dish_management.application.ConsultAvailableDishesController;
import lapr4.cafeteria.dish_management.domain.Dish;
import lapr4.cafeteria.dish_management.domain.DishType;

@SuppressWarnings("java:S106")
public class ConsultAvailableDishesUI extends AbstractUI {

    private final ConsultAvailableDishesController controller = new ConsultAvailableDishesController();

    @Override
    protected boolean doShow() {
        System.out.println("====== Dish Menu ======");
        System.out.println("1 - List all active dishes");
        System.out.println("2 - List active dishes by type");
        System.out.println("0 - Exit");

        int choice = Console.readOption(1, 2, 0);

        switch (choice) {
            case 1:
                showList(controller.activeDishes());
                break;

            case 2:
                final DishType dishType = selectDishType();
                showList(controller.activeDishesByType(dishType));
                break;

            case 0:
                System.out.println("Exiting...");
                break;
        }

        return false;
    }

    private DishType selectDishType() {
        System.out.println("Dish Types Available:");
        for (final DishType type : DishType.values()) {
            System.out.println("\t" + type.toString());
        }

        do {
            try {
                final String type = Console.readLine("Dish Type: ").toUpperCase();
                return DishType.valueOf(type);
            } catch (final IllegalArgumentException e) {
                System.out.println("Please try again. Enter a valid dish type.");
            }
        } while (true);
    }

    private void showList(final Iterable<Dish> dishes) {
        if (!dishes.iterator().hasNext()) {
            System.out.println("There are no available dishes");
            return;
        }
        DishPrinter printer = new DishPrinter();
        System.out.println(printer.header());
        for (Dish dish : dishes) {
            printer.visit(dish);
        }
    }

    @Override
    public String headline() {
        return "Consult Available Dish";
    }

}
