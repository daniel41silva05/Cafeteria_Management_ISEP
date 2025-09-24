package lapr4.cafeteria.app.backoffice.console.presentation.card_accounts;

import eapli.framework.visitor.Visitor;
import lapr4.cafeteria.card_account.domain.CardMovement;

import java.text.SimpleDateFormat;

@SuppressWarnings({ "squid:S106" })
public class CardMovementPrinter implements Visitor<CardMovement> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void visit(final CardMovement visitee) {
        System.out.printf(
                "%-15s %-15s %-10s%n",
                DATE_FORMAT.format(visitee.occurredOn().getTime()),
                visitee.amount(),
                visitee.movementType()
        );
    }

    public String header() {
        return String.format(
                "%-15s %-15s %-10s%n",
                "DATE", "AMOUNT", "TYPE"
        );
    }

}
