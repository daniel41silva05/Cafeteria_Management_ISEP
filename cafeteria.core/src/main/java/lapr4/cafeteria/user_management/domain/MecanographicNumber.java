package lapr4.cafeteria.user_management.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

/**
 * Mecanographic Number is a set of 7 numeric digits.
 */

@Embeddable
@EqualsAndHashCode
public class MecanographicNumber implements ValueObject, Comparable<MecanographicNumber> {

    private static final long serialVersionUID = 1L;

    private String number;

    public MecanographicNumber(final String mecanographicNumber) {
        Preconditions.nonEmpty(mecanographicNumber, "Mecanographic number should neither be null nor empty");
        Preconditions.ensure(isValidMecanographicNumber(mecanographicNumber),
                "Invalid Mecanographic Number. It must be exactly 7 digits.");

        this.number = mecanographicNumber;
    }

    protected MecanographicNumber() {
        // for ORM
    }

    public static MecanographicNumber valueOf(final String number) {
        return new MecanographicNumber(number);
    }

    private static boolean isValidMecanographicNumber(final String number) {
        return number.matches("\\d{7}");
    }

    @Override
    public String toString() {
        return this.number;
    }

    @Override
    public int compareTo(final MecanographicNumber other) {
        return number.compareTo(other.number);
    }
}
