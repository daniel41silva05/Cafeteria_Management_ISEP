package lapr4.cafeteria.app.backoffice.console.presentation.meals;

import eapli.framework.visitor.Visitor;
import lapr4.cafeteria.meal_management.domain.Meal;

@SuppressWarnings({ "squid:S106" })
public class MealPrinter implements Visitor<Meal> {

    @Override
    public void visit(final Meal visitee) {
        System.out.printf(
                "%-15s%-15s%-25s%-15s%-10d%-10d%-30s%-10s%-10s%n",
                visitee.mealType(),
                visitee.ofDay().getTime(),
                visitee.dish().name(),
                visitee.dish().dishType(),
                visitee.dish().nutricionalInfo().calories(),
                visitee.dish().nutricionalInfo().salt(),
                visitee.dish().shortDescription(),
                visitee.dish().price(),
                visitee.dish().isActive() ? "Active" : "Inactive"
        );
    }

    public String header() {
        return String.format(
                "%-15s%-15s%-25s%-15s%-10s%-10s%-30s%-10s%-10s%n",
                "MEAL TYPE", "DATE", "DISH NAME", "DISH TYPE",
                "CALORIES", "SALT", "SHORT DESC", "PRICE", "STATUS"
        );
    }

}
