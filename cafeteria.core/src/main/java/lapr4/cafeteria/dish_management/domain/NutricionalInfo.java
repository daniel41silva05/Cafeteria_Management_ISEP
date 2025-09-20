package lapr4.cafeteria.dish_management.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class NutricionalInfo implements ValueObject {

    private static final long serialVersionUID = 1L;

    private Integer calories;

    private Integer salt;

    public NutricionalInfo(final int calories, final int salt) {
        Preconditions.ensure(calories >= 0, "Calories can't be negative");
        Preconditions.ensure(salt >= 0, "Salt can't be negative");

        this.calories = calories;
        this.salt = salt;
    }

    protected NutricionalInfo() {
        // for ORM
    }

    public static NutricionalInfo valueOf(final int calories, final int salt) {
        return new NutricionalInfo(calories, salt);
    }

}
