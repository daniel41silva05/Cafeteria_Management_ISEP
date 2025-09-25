package lapr4.cafeteria.app.backoffice.console.presentation.meal_bookings;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import lapr4.cafeteria.meal_booking.application.CancelBookingController;
import lapr4.cafeteria.meal_booking.domain.Booking;

import java.util.Calendar;

@SuppressWarnings("java:S106")
public class CancelBookingUI extends AbstractUI {

    private final CancelBookingController controller = new CancelBookingController();

    @Override
    protected boolean doShow() {

        final Calendar day = readValidDate();

        final Iterable<Booking> bookings = controller.bookingsForDay(day);

        if (!bookings.iterator().hasNext()) {
            System.out.println("There are no bookings");
        } else {
            BookingPrinter printer = new BookingPrinter();
            final SelectWidget<Booking> selector = new SelectWidget<>(printer.header(), bookings, printer);
            selector.show();
            Booking selectedBooking = selector.selectedElement();

            if (selectedBooking != null) {
                try {
                    Booking booking = controller.cancelBooking(selectedBooking);
                    showBookingResult(booking);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (IllegalStateException e) {
                    System.out.println("Error: Could not retrieve the current user session.");
                }
            }
        }

        return false;
    }

    private void showBookingResult(final Booking booking) {
        System.out.println("\n=== Registration Successful ===");

        System.out.println("Booking Token: " + booking.identity());
        System.out.println("Status: " + booking.status());
        System.out.println("Cost: " + booking.cost());
        System.out.println("Date: " + booking.meal().ofDay().getTime());
        System.out.println("Meal Type: " + booking.meal().mealType());
        System.out.println("Dish Name: " + booking.meal().dish().name());
        System.out.println("Dish Type: " + booking.meal().dish().dishType());
    }

    private Calendar readValidDate() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        Calendar chosenDate;
        do {
            chosenDate = Console.readCalendar("Day (dd-mm-yyyy): ");
            if (chosenDate.before(today)) {
                System.out.println("The date cannot be in the past. Please try again.");
            }
        } while (chosenDate.before(today));

        return chosenDate;
    }

    @Override
    public String headline() {
        return "Cancel a Booking";
    }

}
