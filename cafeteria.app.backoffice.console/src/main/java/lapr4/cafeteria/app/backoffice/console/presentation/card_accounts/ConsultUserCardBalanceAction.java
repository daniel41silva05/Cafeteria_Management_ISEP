package lapr4.cafeteria.app.backoffice.console.presentation.card_accounts;

import eapli.framework.actions.Action;
import lapr4.cafeteria.app.backoffice.console.presentation.dishes.ActivateDeactivateDishUI;

public class ConsultUserCardBalanceAction implements Action {

    @Override
    public boolean execute() {
        return new ConsultUserCardBalanceUI().show();
    }
}
