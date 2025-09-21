package lapr4.cafeteria.meal_management.application;

import eapli.framework.application.ApplicationService;
import lapr4.cafeteria.infrastructure.persistence.PersistenceContext;
import lapr4.cafeteria.meal_management.domain.Meal;
import lapr4.cafeteria.meal_management.domain.MealType;
import lapr4.cafeteria.meal_management.repositories.MealRepository;

import java.util.Calendar;

@ApplicationService
public class ListMealService {

    private final MealRepository repo = PersistenceContext.repositories().meals();

    public Iterable<Meal> allMealsForDay(final Calendar forDay) {
        return repo.findByDay(forDay);
    }

    public Iterable<Meal> allMealsForPeriod(final Calendar beginDate, final Calendar endDate) {
        return repo.findByPeriod(beginDate, endDate);
    }

    public Iterable<Meal> allMealsForDayAndType(final Calendar forDay, final MealType mealType) {
        return repo.findByDayAndType(forDay, mealType);
    }

}
