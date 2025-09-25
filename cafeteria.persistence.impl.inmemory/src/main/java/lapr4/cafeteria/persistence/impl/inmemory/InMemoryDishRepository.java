package lapr4.cafeteria.persistence.impl.inmemory;

import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import lapr4.cafeteria.dish_management.domain.Dish;
import lapr4.cafeteria.dish_management.domain.DishType;
import lapr4.cafeteria.dish_management.repositories.DishRepository;

public class InMemoryDishRepository extends InMemoryDomainRepository<Dish, Designation>
        implements DishRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Dish> findAllActive() {
        return match(Dish::isActive);
    }

    @Override
    public Iterable<Dish> findAllActiveByType(DishType type) {
        return match(dish -> dish.isActive() && dish.dishType().equals(type));
    }

    @Override
    public Iterable<Dish> findAllDisabled() {
        return match(dish -> !dish.isActive());
    }

}
