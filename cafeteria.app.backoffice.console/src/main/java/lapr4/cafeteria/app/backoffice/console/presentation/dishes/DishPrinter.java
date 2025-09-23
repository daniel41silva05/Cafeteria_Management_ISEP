package lapr4.cafeteria.app.backoffice.console.presentation.dishes;

import eapli.framework.visitor.Visitor;
import lapr4.cafeteria.dish_management.domain.Dish;

@SuppressWarnings({ "squid:S106" })
public class DishPrinter implements Visitor<Dish> {

    @Override
    public void visit(final Dish visitee) {
        System.out.printf(
                "%-25s%-15s%-10d%-10d%-30s%-10s%-10s%n",
                visitee.name(),
                visitee.dishType(),
                visitee.nutricionalInfo().calories(),
                visitee.nutricionalInfo().salt(),
                visitee.shortDescription(),
                visitee.price(),
                visitee.isActive() ? "Active" : "Inactive"
        );
    }

    public String header() {
        return String.format(
                "%-25s%-15s%-10s%-10s%-30s%-10s%-10s%n",
                "NAME", "TYPE", "CALORIES", "SALT", "SHORT DESC", "PRICE", "STATUS"
        );
    }

}
