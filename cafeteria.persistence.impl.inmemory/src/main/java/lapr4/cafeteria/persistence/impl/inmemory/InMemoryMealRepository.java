package lapr4.cafeteria.persistence.impl.inmemory;

import eapli.framework.identities.impl.NumberSequenceGenerator;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import lapr4.cafeteria.meal_management.domain.Meal;
import lapr4.cafeteria.meal_management.domain.MealType;
import lapr4.cafeteria.meal_management.repositories.MealRepository;

import java.util.Calendar;

public class InMemoryMealRepository extends InMemoryDomainRepository<Meal, Long>
        implements MealRepository {

    static {
        InMemoryInitializer.init();
    }

    private static final NumberSequenceGenerator gen = new NumberSequenceGenerator();

    public InMemoryMealRepository() {
        super(e -> gen.newId());
    }

    @Override
    public Iterable<Meal> findByDay(Calendar forDay) {
        return match(meal -> sameDay(meal.ofDay(), forDay));
    }

    @Override
    public Iterable<Meal> findByPeriod(Calendar beginDate, Calendar endDate) {
        return match(meal -> !meal.ofDay().before(beginDate) && !meal.ofDay().after(endDate));
    }

    @Override
    public Iterable<Meal> findByDayAndType(Calendar forDay, MealType mealType) {
        return match(meal -> sameDay(meal.ofDay(), forDay) && meal.mealType().equals(mealType));
    }

    private boolean sameDay(Calendar c1, Calendar c2) {
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
    }

}
