package lapr4.cafeteria.app.backoffice.console.presentation.card_accounts;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import lapr4.cafeteria.card_account.application.ConsultUserCardMovementsController;
import lapr4.cafeteria.card_account.domain.CardMovement;

import java.util.Calendar;

@SuppressWarnings("java:S106")
public class ConsultUserCardMovementsUI extends AbstractUI {

    private final ConsultUserCardMovementsController controller = new ConsultUserCardMovementsController();

    @Override
    protected boolean doShow() {
        System.out.println("====== Card Movement Menu ======");
        System.out.println("1 - Consult all card movements");
        System.out.println("2 - Consult card movements for period");
        System.out.println("0 - Exit");

        int choice = Console.readOption(1, 2, 0);

        switch (choice) {
            case 1:
                showList(controller.consultAllCardMovements());
                break;

            case 2:
                final Calendar beginDay = Console.readCalendar("Begin Day: ");
                final Calendar endDay = Console.readCalendar("End Day: ");
                showList(controller.consultCardMovementsOnPeriod(beginDay, endDay));
                break;

            case 0:
                System.out.println("Exiting...");
                break;
        }

        return false;
    }

    private void showList(final Iterable<CardMovement> movements) {
        if (!movements.iterator().hasNext()) {
            System.out.println("There are no movements");
            return;
        }
        CardMovementPrinter printer = new CardMovementPrinter();
        System.out.println(printer.header());
        for (CardMovement movement : movements) {
            printer.visit(movement);
        }
    }

    @Override
    public String headline() {
        return "Consult Card Movements";
    }

}
