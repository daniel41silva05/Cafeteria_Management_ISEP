package lapr4.cafeteria.app.backoffice.console.presentation.card_accounts;

import eapli.framework.presentation.console.AbstractUI;
import lapr4.cafeteria.card_account.application.ConsultUserCardBalanceController;

import java.math.BigDecimal;

@SuppressWarnings("java:S106")
public class ConsultUserCardBalanceUI extends AbstractUI {

    private final ConsultUserCardBalanceController controller = new ConsultUserCardBalanceController();

    @Override
    protected boolean doShow() {
        try {
            BigDecimal result = controller.consultCardBalance();

            System.out.println("\n=== Card Balance ===");
            System.out.println("Your current balance is: " + result + " €");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Error: Could not retrieve the current user session.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Consult Card Balance";
    }

}
