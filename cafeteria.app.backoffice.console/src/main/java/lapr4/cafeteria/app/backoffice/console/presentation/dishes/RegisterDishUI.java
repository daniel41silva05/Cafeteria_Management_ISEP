package lapr4.cafeteria.app.backoffice.console.presentation.dishes;

import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import lapr4.cafeteria.dish_management.application.RegisterDishController;
import lapr4.cafeteria.dish_management.domain.Dish;
import lapr4.cafeteria.dish_management.domain.DishType;

@SuppressWarnings("java:S106")
public class RegisterDishUI extends AbstractUI {

    private final RegisterDishController controller = new RegisterDishController();

    @Override
    protected boolean doShow() {
        final String name = Console.readLine("Name: ");
        final DishType dishType = selectDishType();
        final double price = Console.readDouble("Price: ");
        final String shortDescription = Console.readLine("Short Description: ");
        final String longDescription = Console.readLine("Long Description: ");
        final int calories = Console.readInteger("Calories: ");
        final int salt = Console.readInteger("Salt: ");

        try {
            Dish dish = controller.registerDish(name, dishType, price, shortDescription, longDescription, calories, salt);
            showDishResult(dish);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("That name is already in use.");
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

    private void showDishResult(final Dish dish) {
        System.out.println("\n=== Registration Successful ===");

        System.out.println("Name: " + dish.identity().toString());
        System.out.println("Dish Type: " + dish.dishType().toString());
        System.out.println("Price: " + dish.price().toString());
        System.out.println("Short Description: " + dish.shortDescription().toString());
        System.out.println("Calories: " + dish.nutricionalInfo().calories().toString());
        System.out.println("Salt: " + dish.nutricionalInfo().salt().toString());
    }

    @Override
    public String headline() {
        return "Register Dish";
    }

}
