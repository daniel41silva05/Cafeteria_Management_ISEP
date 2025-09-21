package lapr4.cafeteria.card_account.domain;

import java.io.Serial;
import java.util.Calendar;
import java.util.UUID;

import jakarta.persistence.*;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.money.domain.model.Money;
import eapli.framework.validations.Preconditions;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;

@Entity
public class CardBalance implements AggregateRoot<String> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private final String id = UUID.randomUUID().toString();

    @Version
    private Long version;

    @OneToOne
    private CafeteriaUser user;

    private Money balance;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastUpdated;

    public CardBalance(final CafeteriaUser user, final Money initialBalance) {
        Preconditions.noneNull(user, initialBalance);

        this.user = user;
        this.balance = initialBalance;
        this.lastUpdated = Calendar.getInstance();
    }

    protected CardBalance() {
        // for ORM only
    }

    public void applyMovement(Money amount) {
        Preconditions.nonNull(amount);

        this.balance = this.balance.add(amount);
        this.lastUpdated = Calendar.getInstance();
    }

    @Override
    public String identity() {
        return id;
    }

    public Money balance() {
        return balance;
    }

    public CafeteriaUser user() {
        return user;
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

