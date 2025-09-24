package lapr4.cafeteria.infrastructure.bootstrapers.demo;

import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Designation;
import lapr4.cafeteria.dish_management.domain.Dish;
import lapr4.cafeteria.infrastructure.bootstrapers.TestDataConstants;
import lapr4.cafeteria.meal_management.application.RegisterMealController;
import lapr4.cafeteria.meal_management.domain.MealType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;

public class MealBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(MealBootstrapper.class);

    @Override
    public boolean execute() {
        final Calendar today = Calendar.getInstance();

        Dish meatDish = new Dish(Designation.valueOf(TestDataConstants.DISH_NAME_MEAT), null, null, null, null, null);
        Dish fishDish = new Dish(Designation.valueOf(TestDataConstants.DISH_NAME_FISH), null, null, null, null, null);
        Dish vegetarianDish = new Dish(Designation.valueOf(TestDataConstants.DISH_NAME_VEGETARIAN), null, null, null, null, null);

        registerMeal(meatDish, MealType.LUNCH, today);
        registerMeal(vegetarianDish, MealType.LUNCH, today);
        registerMeal(meatDish, MealType.DINNER, today);
        registerMeal(fishDish, MealType.DINNER, today);

        return true;
    }

    private void registerMeal(final Dish dish, final MealType mealType, final Calendar ofDay) {
        final RegisterMealController controller = new RegisterMealController();

        try {
            controller.registerMeal(dish, mealType, ofDay);
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            LOGGER.warn("Assuming meal '{}' already exists (activate trace log for details)", dish.name());
            LOGGER.trace("Assuming existing record", ex);
        }
    }
}
