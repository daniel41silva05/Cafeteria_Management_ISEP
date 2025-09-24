package lapr4.cafeteria.app.backoffice.console.presentation.card_accounts;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import lapr4.cafeteria.card_account.application.RechargeUserCardController;
import lapr4.cafeteria.card_account.domain.CardMovement;

@SuppressWarnings("java:S106")
public class RechargeUserCardUI extends AbstractUI {

    private final RechargeUserCardController controller = new RechargeUserCardController();

    @Override
    protected boolean doShow() {
        final String mecanographicNumber = Console.readLine("Mecanographic Number: ");
        final double amount = Console.readDouble("Amount: ");

        try {
            CardMovement result = controller.rechargeUserCard(mecanographicNumber, amount);
            showMovement(result);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Error: Could not retrieve the current user session.");
        }

        return false;
    }

    private void showMovement(final CardMovement movement) {
        CardMovementPrinter printer = new CardMovementPrinter();
        System.out.println(printer.header());
        printer.visit(movement);
    }

    @Override
    public String headline() {
        return "Recharge User Card";
    }

}
