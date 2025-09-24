package lapr4.cafeteria.infrastructure.bootstrapers.demo;

import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import lapr4.cafeteria.dish_management.application.RegisterDishController;
import lapr4.cafeteria.dish_management.domain.DishType;
import lapr4.cafeteria.infrastructure.bootstrapers.TestDataConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DishBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(DishBootstrapper.class);

    @Override
    public boolean execute() {
        registerDish(TestDataConstants.DISH_NAME_MEAT, DishType.MEAT, 12.5,
                "Juicy grilled steak with crispy fries",
                "Premium beef steak grilled to perfection, served with golden fries.",
                950, 3);

        registerDish(TestDataConstants.DISH_NAME_FISH, DishType.FISH, 11.0,
                "Fresh grilled salmon with lemon",
                "Delicious salmon fillet grilled and served with a light lemon butter sauce.",
                700, 2);

        registerDish(TestDataConstants.DISH_NAME_VEGETARIAN, DishType.VEGETARIAN, 9.0,
                "Homemade vegetable lasagna",
                "Layers of fresh vegetables, pasta, tomato sauce and mozzarella cheese.",
                600, 2);

        return true;
    }

    private void registerDish(final String name, final DishType dishType, final double price,
                              final String shortDescription, final String longDescription,
                              final int calories, final int salt) {
        final RegisterDishController controller = new RegisterDishController();

        try {
            controller.registerDish(name, dishType, price, shortDescription, longDescription, calories, salt);
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            LOGGER.warn("Assuming dish '{}' already exists (activate trace log for details)", name);
            LOGGER.trace("Assuming existing record", ex);
        }
    }

}
