package lapr4.cafeteria.dish_management.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import lapr4.cafeteria.dish_management.domain.Dish;
import lapr4.cafeteria.dish_management.domain.DishType;
import lapr4.cafeteria.user_management.domain.CafeteriaRoles;

@UseCaseController
public class ConsultAvailableDishesController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListDishService svc = new ListDishService();

    public Iterable<Dish> activeDishes() {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.MENU_MANAGER);
        return svc.allActiveDishes();
    }

    public Iterable<Dish> activeDishesByType(final DishType type) {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.MENU_MANAGER);
        return svc.allActiveDishesByType(type);
    }

}
