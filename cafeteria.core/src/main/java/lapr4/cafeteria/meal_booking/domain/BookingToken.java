package lapr4.cafeteria.meal_booking.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Embeddable
@EqualsAndHashCode
public class BookingToken implements ValueObject, Comparable<BookingToken> {

    private static final long serialVersionUID = -1820803667379631580L;

    private String token;

    public BookingToken(final String value) {
        Preconditions.nonEmpty(value);
        token = UUID.fromString(value).toString();
    }

    protected BookingToken() {
        // for ORM
    }

    public static BookingToken valueOf(final String value) {
        return new BookingToken(value);
    }

    public static BookingToken newToken() {
        return valueOf(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return token;
    }

    @Override
    public int compareTo(final BookingToken other) {
        return token.compareTo(other.token);
    }

}
