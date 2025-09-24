package lapr4.cafeteria.app.backoffice.console.presentation.meal_bookings;

import eapli.framework.actions.Action;
import lapr4.cafeteria.app.backoffice.console.presentation.meals.ConsultMealsUI;

public class CancelBookingAction implements Action {

    @Override
    public boolean execute() {
        return new CancelBookingUI().show();
    }
}
