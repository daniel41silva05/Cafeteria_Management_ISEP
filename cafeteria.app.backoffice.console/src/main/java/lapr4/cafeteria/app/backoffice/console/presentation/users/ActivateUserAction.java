package lapr4.cafeteria.app.backoffice.console.presentation.users;

import eapli.framework.actions.Action;

public class ActivateUserAction implements Action {

    @Override
    public boolean execute() {
        return new ActivateUserUI().show();
    }
}
