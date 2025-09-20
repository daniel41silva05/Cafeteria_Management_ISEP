package lapr4.cafeteria.meal_management.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.validations.Preconditions;
import lapr4.cafeteria.dish_management.application.ListDishService;
import lapr4.cafeteria.dish_management.domain.Dish;
import lapr4.cafeteria.infrastructure.persistence.PersistenceContext;
import lapr4.cafeteria.meal_management.domain.Meal;
import lapr4.cafeteria.meal_management.domain.MealType;
import lapr4.cafeteria.meal_management.repositories.MealRepository;
import lapr4.cafeteria.user_management.domain.CafeteriaRoles;

import java.util.Calendar;

@UseCaseController
public class RegisterMealController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final MealRepository repo = PersistenceContext.repositories().meals();
    private final ListDishService svc = new ListDishService();

    public Iterable<Dish> listDish() {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.MENU_MANAGER);
        return svc.allActiveDishes();
    }

    public Meal registerMeal(final Dish dish, final MealType mealType, final Calendar ofDay) {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.MENU_MANAGER);

        Meal newMeal = new Meal(dish, mealType, ofDay);

        return repo.save(newMeal);
    }

}
