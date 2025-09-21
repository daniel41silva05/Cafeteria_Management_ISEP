package lapr4.cafeteria.meal_booking.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.money.domain.model.Money;
import eapli.framework.validations.Invariants;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import lapr4.cafeteria.meal_management.domain.Meal;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;

import java.io.Serial;

@Entity
public class Booking implements AggregateRoot<BookingToken> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    @Version
    private Long version;

    @Column(unique = true)
    private BookingToken token;

    @ManyToOne
    private CafeteriaUser user;

    @ManyToOne
    private Meal meal;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private Money cost;

    public Booking(final CafeteriaUser user, final Meal meal) {
        Preconditions.noneNull(user, meal);

        this.token = BookingToken.newToken();
        this.user = user;
        this.meal = meal;
        this.status = BookingStatus.BOOKED;
        this.cost = meal.dish().price();
    }

    protected Booking() {
        // for ORM only
    }

    @Override
    public BookingToken identity() {
        return token;
    }

    public boolean isBooked() {
        return status == BookingStatus.BOOKED;
    }

    public void cancel() {
        Invariants.ensure(status == BookingStatus.BOOKED, "A booking can only be canceled if currently booked");
        status = BookingStatus.CANCELED;
    }

    public void deliver() {
        Invariants.ensure(status == BookingStatus.BOOKED, "A booking can only be delivered if currently booked");
        status = BookingStatus.DELIVERED;
    }

    public CafeteriaUser user() {
        return user;
    }

    public Meal meal() {
        return meal;
    }

    public BookingStatus status() {
        return status;
    }

    public Money cost() {
        return cost;
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
