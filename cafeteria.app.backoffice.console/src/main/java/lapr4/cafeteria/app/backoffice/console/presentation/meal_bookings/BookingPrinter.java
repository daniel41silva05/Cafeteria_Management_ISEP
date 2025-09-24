package lapr4.cafeteria.app.backoffice.console.presentation.meal_bookings;

import eapli.framework.visitor.Visitor;
import lapr4.cafeteria.meal_booking.domain.Booking;

import java.text.SimpleDateFormat;

@SuppressWarnings({ "squid:S106" })
public class BookingPrinter implements Visitor<Booking> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void visit(final Booking visitee) {
        System.out.printf(
                "%-15s %-10s %-10s %-15s %-15s %-25s %-15s%n",
                visitee.identity(),
                visitee.status(),
                visitee.cost(),
                DATE_FORMAT.format(visitee.meal().ofDay().getTime()),
                visitee.meal().mealType(),
                visitee.meal().dish().name(),
                visitee.meal().dish().dishType()
        );
    }

    public String header() {
        return String.format(
                "%-15s %-10s %-10s %-15s %-15s %-25s %-15s%n",
                "BOOKING", "STATUS", "COST", "DATE", "MEAL TYPE", "DISH NAME", "DISH TYPE"
        );
    }
}
