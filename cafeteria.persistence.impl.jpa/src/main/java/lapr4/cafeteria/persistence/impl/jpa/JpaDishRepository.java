package lapr4.cafeteria.persistence.impl.jpa;

import eapli.framework.general.domain.model.Designation;
import lapr4.cafeteria.dish_management.domain.Dish;
import lapr4.cafeteria.dish_management.domain.DishType;
import lapr4.cafeteria.dish_management.repositories.DishRepository;

class JpaDishRepository extends CafeteriaJpaRepositoryBase<Dish, Long, Designation>
        implements DishRepository {

    public JpaDishRepository() {
        super("name");
    }

    @Override
    public Iterable<Dish> findAllActive() {
        return match("e.active = true");
    }

    @Override
    public Iterable<Dish> findAllActiveByType(final DishType type) {
        return match("e.active = true AND e.dishType = :type", "type", type);
    }

    @Override
    public Iterable<Dish> findAllDisabled() {
        return match("e.active = false");
    }

}
