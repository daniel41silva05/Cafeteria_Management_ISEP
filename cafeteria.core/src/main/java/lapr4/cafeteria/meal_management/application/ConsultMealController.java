package lapr4.cafeteria.meal_management.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import lapr4.cafeteria.meal_management.domain.Meal;
import lapr4.cafeteria.meal_management.domain.MealType;
import lapr4.cafeteria.user_management.domain.CafeteriaRoles;

import java.util.Calendar;

@UseCaseController
public class ConsultMealController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListMealService svc = new ListMealService();

    public Iterable<Meal> mealsForDay(final Calendar forDay) {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.MENU_MANAGER, CafeteriaRoles.CAFETERIA_USER);
        return svc.allMealsForDay(forDay);
    }

    public Iterable<Meal> mealsForPeriod(final Calendar beginDate, final Calendar endDate) {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.MENU_MANAGER, CafeteriaRoles.CAFETERIA_USER);
        return svc.allMealsForPeriod(beginDate, endDate);
    }

    public Iterable<Meal> mealsForDayAndType(final Calendar forDay, final MealType mealType) {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.MENU_MANAGER, CafeteriaRoles.CAFETERIA_USER);
        return svc.allMealsForDayAndType(forDay, mealType);
    }

}
