package lapr4.cafeteria.dish_management.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.money.domain.model.Money;
import eapli.framework.validations.Preconditions;
import lapr4.cafeteria.dish_management.domain.Dish;
import lapr4.cafeteria.dish_management.domain.DishType;
import lapr4.cafeteria.dish_management.domain.NutricionalInfo;
import lapr4.cafeteria.dish_management.repositories.DishRepository;
import lapr4.cafeteria.infrastructure.persistence.PersistenceContext;
import lapr4.cafeteria.user_management.domain.CafeteriaRoles;

@UseCaseController
public class RegisterDishController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final DishRepository repo = PersistenceContext.repositories().dishes();

    public Dish registerDish(final String name, final DishType dishType, final double price, final String shortDescription,
                             final int calories, final int salt) {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.MENU_MANAGER);

        Dish newDish = new Dish(Designation.valueOf(name), dishType, Description.valueOf(shortDescription),
                Money.euros(price), NutricionalInfo.valueOf(calories, salt));

        return repo.save(newDish);
    }

}
