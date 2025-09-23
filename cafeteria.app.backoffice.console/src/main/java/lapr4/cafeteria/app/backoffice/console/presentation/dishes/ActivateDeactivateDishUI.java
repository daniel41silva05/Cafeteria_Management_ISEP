package lapr4.cafeteria.app.backoffice.console.presentation.dishes;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import lapr4.cafeteria.dish_management.application.ActivateDeactivateDishController;
import lapr4.cafeteria.dish_management.domain.Dish;

@SuppressWarnings("squid:S106")
public class ActivateDeactivateDishUI extends AbstractUI {

    private final ActivateDeactivateDishController controller = new ActivateDeactivateDishController();

    @Override
    protected boolean doShow() {
        final Iterable<Dish> dishes = controller.listDish();

        if (!dishes.iterator().hasNext()) {
            System.out.println("There are no dishes");
        } else {
            DishPrinter printer = new DishPrinter();
            final SelectWidget<Dish> selector = new SelectWidget<>(printer.header(), dishes, printer);
            selector.show();
            final Dish selectedDish = selector.selectedElement();
            if (selectedDish != null) {
                try {
                    Dish dish = controller.changeDishState(selectedDish);
                    showDishResult(dish);
                } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                    System.out.println(
                            "WARNING: That entity has already been changed or deleted since you last read it");
                }
            }
        }

        return false;
    }

    private void showDishResult(final Dish dish) {
        System.out.println("\n=== Registration Successful ===");

        System.out.println("Name: " + dish.identity().toString());
        System.out.println("State: " + (dish.isActive() ? "Active" : "Inactive"));
        System.out.println("Dish Type: " + dish.dishType().toString());
        System.out.println("Price: " + dish.price().toString());
        System.out.println("Short Description: " + dish.shortDescription().toString());
        System.out.println("Calories: " + dish.nutricionalInfo().calories().toString());
        System.out.println("Salt: " + dish.nutricionalInfo().salt().toString());
    }

    @Override
    public String headline() {
        return "Activate/Deactivate Dish";
    }

}
