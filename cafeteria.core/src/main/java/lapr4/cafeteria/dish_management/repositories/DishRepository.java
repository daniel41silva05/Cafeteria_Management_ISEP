package lapr4.cafeteria.dish_management.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.Designation;
import lapr4.cafeteria.dish_management.domain.Dish;
import lapr4.cafeteria.dish_management.domain.DishType;

public interface DishRepository extends DomainRepository<Designation, Dish> {

    Iterable<Dish> findAllActive();

    Iterable<Dish> findAllActiveByType(DishType type);

    Iterable<Dish> findAllDisabled();

}
