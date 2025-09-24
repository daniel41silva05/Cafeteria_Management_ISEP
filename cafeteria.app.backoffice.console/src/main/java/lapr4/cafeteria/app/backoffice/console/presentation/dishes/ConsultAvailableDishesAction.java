package lapr4.cafeteria.app.backoffice.console.presentation.dishes;

import eapli.framework.actions.Action;
import lapr4.cafeteria.app.backoffice.console.presentation.meal_bookings.BookMealUI;

public class ConsultAvailableDishesAction implements Action {

    @Override
    public boolean execute() {
        return new ConsultAvailableDishesUI().show();
    }
}
