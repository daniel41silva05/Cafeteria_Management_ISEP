package lapr4.cafeteria.meal_management.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import lapr4.cafeteria.dish_management.domain.Dish;

import java.io.Serial;
import java.util.Calendar;

/**
 * Meal is a temporal instantiation of a Dish and a given Meal Type,
 * for example, Bacalhau à Brás from Lunch on February 16, 2016
 */

@Entity
public class Meal implements AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    @ManyToOne
    private Dish dish;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    @Temporal(TemporalType.DATE)
    private Calendar ofDay;

    public Meal(final Dish dish, final MealType mealType, final Calendar ofDay) {
        Preconditions.noneNull(dish, mealType, ofDay);

        this.dish = dish;
        this.mealType = mealType;
        this.ofDay = ofDay;
    }

    protected Meal() {
        // for ORM only
    }

    @Override
    public Long identity() {
        return id;
    }

    public Dish dish() {
        return dish;
    }

    public MealType mealType() {
        return mealType;
    }

    public Calendar ofDay() {
        return ofDay;
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

}
