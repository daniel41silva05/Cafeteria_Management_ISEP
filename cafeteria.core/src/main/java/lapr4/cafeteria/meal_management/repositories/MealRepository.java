package lapr4.cafeteria.meal_management.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import lapr4.cafeteria.meal_management.domain.Meal;
import lapr4.cafeteria.meal_management.domain.MealType;

import java.util.Calendar;

public interface MealRepository extends DomainRepository<Long, Meal> {

    Iterable<Meal> findByDay(Calendar forDay);

    Iterable<Meal> findByPeriod(Calendar beginDate, Calendar endDate);

    Iterable<Meal> findByDayAndType(Calendar forDay, MealType mealType);

}
