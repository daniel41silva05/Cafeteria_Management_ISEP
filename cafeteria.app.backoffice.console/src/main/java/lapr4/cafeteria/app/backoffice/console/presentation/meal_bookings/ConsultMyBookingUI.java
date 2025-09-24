package lapr4.cafeteria.app.backoffice.console.presentation.meal_bookings;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import lapr4.cafeteria.meal_booking.application.ConsultMyBookingController;
import lapr4.cafeteria.meal_booking.domain.Booking;

import java.util.Calendar;

@SuppressWarnings("java:S106")
public class ConsultMyBookingUI extends AbstractUI {

    private final ConsultMyBookingController controller = new ConsultMyBookingController();

    @Override
    protected boolean doShow() {
        System.out.println("====== Booking Menu ======");
        System.out.println("1 - Consult bookings for day");
        System.out.println("2 - Consult bookings for period");
        System.out.println("0 - Exit");

        int choice = Console.readOption(1, 2, 0);

        try {
            switch (choice) {
                case 1:
                    final Calendar day = Console.readCalendar("Day: ");
                    showList(controller.bookingsByUserForDay(day));
                    break;

                case 2:
                    final Calendar beginDay = Console.readCalendar("Begin Day: ");
                    final Calendar endDay = Console.readCalendar("End Day: ");
                    showList(controller.bookingsByUserForPeriod(beginDay, endDay));
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Error: Could not retrieve the current user session.");
        }

        return false;
    }

    private void showList(final Iterable<Booking> bookings) {
        if (!bookings.iterator().hasNext()) {
            System.out.println("There are no bookings");
            return;
        }
        BookingPrinter printer = new BookingPrinter();
        System.out.println(printer.header());
        for (Booking booking : bookings) {
            printer.visit(booking);
        }
    }

    @Override
    public String headline() {
        return "Consult My Bookings";
    }

}
