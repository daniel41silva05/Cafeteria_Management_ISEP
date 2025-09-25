package lapr4.cafeteria.dish_management.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.money.domain.model.Money;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;

import java.io.Serial;

@Entity
public class Dish implements AggregateRoot<Designation> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    @Version
    private Long version;

    @Column(unique = true)
    private Designation name;

    @Enumerated(EnumType.STRING)
    private DishType dishType;

    private NutricionalInfo nutricionalInfo;

    private Description shortDescription;

    private Money price;

    private boolean active;

    public Dish(final Designation name, final DishType dishType, final Description shortDescription,
                final Money price, final NutricionalInfo nutricionalInfo) {
        Preconditions.noneNull(name, dishType, shortDescription, price);

        this.name = name;
        this.dishType = dishType;
        this.nutricionalInfo = nutricionalInfo;
        this.shortDescription = shortDescription;
        this.price = price;
        this.active = true;
    }

    protected Dish() {
        // for ORM only
    }

    @Override
    public Designation identity() {
        return name();
    }

    public void changePriceTo(final Money newPrice) {
        price = newPrice;
    }

    public void changeNutricionalInfoTo(final NutricionalInfo newNutricionalInfo) {
        nutricionalInfo = newNutricionalInfo;
    }

    public boolean toogleState() {
        active = !active;
        return isActive();
    }

    public DishType dishType() {
        return dishType;
    }

    public Designation name() {
        return name;
    }

    public NutricionalInfo nutricionalInfo() {
        return nutricionalInfo;
    }

    public Description shortDescription() {
        return shortDescription;
    }

    public Money price() {
        return price;
    }

    public boolean isActive() {
        return active;
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
