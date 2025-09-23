package lapr4.cafeteria.persistence.impl.jpa;

import lapr4.cafeteria.meal_management.domain.Meal;
import lapr4.cafeteria.meal_management.domain.MealType;
import lapr4.cafeteria.meal_management.repositories.MealRepository;

import java.util.Calendar;

class JpaMealRepository extends CafeteriaJpaRepositoryBase<Meal, Long, Long>
        implements MealRepository {

    public JpaMealRepository() {
        super("id");
    }

    @Override
    public Iterable<Meal> findByDay(final Calendar forDay) {
        return match("e.ofDay = :day", "day", forDay);
    }

    @Override
    public Iterable<Meal> findByDayAndType(final Calendar forDay, final MealType mealType) {
        return match("e.ofDay = :day AND e.mealType = :type",
                new String[]{"day", "type"},
                new Object[]{forDay, mealType});
    }

    @Override
    public Iterable<Meal> findByPeriod(final Calendar beginDate, final Calendar endDate) {
        return match("e.ofDay BETWEEN :begin AND :end",
                new String[]{"begin", "end"},
                new Object[]{beginDate, endDate});
    }

}
