package lapr4.cafeteria.app.backoffice.console.presentation.meal_bookings;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import lapr4.cafeteria.app.backoffice.console.presentation.card_accounts.CardMovementPrinter;
import lapr4.cafeteria.card_account.application.RechargeUserCardController;
import lapr4.cafeteria.card_account.domain.CardMovement;
import lapr4.cafeteria.meal_booking.application.MarkBookingAsDeliveredController;
import lapr4.cafeteria.meal_booking.domain.Booking;

@SuppressWarnings("java:S106")
public class MarkBookingAsDeliveredUI extends AbstractUI {

    private final MarkBookingAsDeliveredController controller = new MarkBookingAsDeliveredController();

    @Override
    protected boolean doShow() {
        final String token = Console.readLine("Booking Token: ");

        try {
            Booking booking = controller.bookingByToken(token);
            showBookingResult(booking);

            final boolean confirms = Console.readBoolean("Do you want to mark booking as delivered? (y/n)");
            if (confirms) {
                showBookingResult(controller.markBookingAsDelivered(booking));
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }

    private void showBookingResult(final Booking booking) {
        System.out.println("Booking Token: " + booking.identity());
        System.out.println("Status: " + booking.status());
        System.out.println("Cost: " + booking.cost());
        System.out.println("Date: " + booking.meal().ofDay().getTime());
        System.out.println("Meal Type: " + booking.meal().mealType());
        System.out.println("Dish Name: " + booking.meal().dish().name());
        System.out.println("Dish Type: " + booking.meal().dish().dishType());
    }

    @Override
    public String headline() {
        return "Mark Booking as Delivered";
    }

}
