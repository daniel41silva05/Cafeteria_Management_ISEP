package lapr4.cafeteria.card_account.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.money.domain.model.Money;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;

import java.io.Serial;
import java.util.Calendar;
import java.util.UUID;

@Entity
public class CardMovement implements AggregateRoot<String> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private final String id = UUID.randomUUID().toString();

    @Version
    private Long version;

    @ManyToOne
    private CafeteriaUser user;

    @Enumerated(EnumType.STRING)
    private MovementType type;

    private Money amount;

    @Temporal(TemporalType.DATE)
    private Calendar occurredOn;

    public CardMovement(final MovementType type, final Money amount, final CafeteriaUser userCard) {
        this(type, amount, userCard, Calendar.getInstance());
    }

    public CardMovement(final MovementType type, final Money amount, final CafeteriaUser user,
                        final Calendar dateOccurrence) {
        Preconditions.noneNull(type, amount, user, dateOccurrence);

        this.type = type;
        if (type == MovementType.PURCHASE && !amount.isNegative()) {
            this.amount = amount.negate();
        } else {
            this.amount = amount;
        }
        this.user = user;
        this.occurredOn = dateOccurrence;
    }

    protected CardMovement() {
        // for ORM tool only
    }

    public CafeteriaUser account() {
        return user;
    }

    public MovementType movementType() {
        return type;
    }

    public Calendar occurredOn() {
        return occurredOn;
    }

    public Money amount() {
        return amount;
    }

    public Money accountingValue() {
        return type == MovementType.PURCHASE ? amount.negate() : amount;
    }

    @Override
    public String identity() {
        return id;
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

}

