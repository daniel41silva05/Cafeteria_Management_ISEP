package lapr4.cafeteria.dish_management.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.validations.Preconditions;
import lapr4.cafeteria.dish_management.domain.Dish;
import lapr4.cafeteria.dish_management.repositories.DishRepository;
import lapr4.cafeteria.infrastructure.persistence.PersistenceContext;
import lapr4.cafeteria.user_management.domain.CafeteriaRoles;

@UseCaseController
public class ActivateDeactivateDishController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final DishRepository repo = PersistenceContext.repositories().dishes();
    private final ListDishService svc = new ListDishService();

    public Iterable<Dish> listDish() {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.MENU_MANAGER);
        return svc.allDishes();
    }

    public Dish changeDishState(final Dish dish) {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.MENU_MANAGER);
        Preconditions.nonNull(dish);

        dish.toogleState();

        return repo.save(dish);
    }

}
