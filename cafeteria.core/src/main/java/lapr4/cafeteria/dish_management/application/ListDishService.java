package lapr4.cafeteria.dish_management.application;

import eapli.framework.application.ApplicationService;
import lapr4.cafeteria.dish_management.domain.Dish;
import lapr4.cafeteria.dish_management.domain.DishType;
import lapr4.cafeteria.dish_management.repositories.DishRepository;
import lapr4.cafeteria.infrastructure.persistence.PersistenceContext;

@ApplicationService
public class ListDishService {

    private final DishRepository repo = PersistenceContext.repositories().dishes();

    public Iterable<Dish> allDishes() {
        return repo.findAll();
    }

    public Iterable<Dish> allActiveDishes() {
        return repo.findAllActive();
    }

    public Iterable<Dish> allActiveDishesByType(DishType type) {
        return repo.findAllActiveByType(type);
    }

}
