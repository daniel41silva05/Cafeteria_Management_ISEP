package lapr4.cafeteria.app.backoffice.console.presentation.users;

import eapli.framework.actions.Action;

public class DeactivateUserAction implements Action {

    @Override
    public boolean execute() {
        return new DeactivateUserUI().show();
    }
}
