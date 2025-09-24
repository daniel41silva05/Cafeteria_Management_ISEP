package lapr4.cafeteria.app.backoffice.console.presentation.meals;

import eapli.framework.actions.Action;
import lapr4.cafeteria.app.backoffice.console.presentation.users.ActivateUserUI;

public class RegisterMealAction implements Action {

    @Override
    public boolean execute() {
        return new RegisterMealUI().show();
    }

}
