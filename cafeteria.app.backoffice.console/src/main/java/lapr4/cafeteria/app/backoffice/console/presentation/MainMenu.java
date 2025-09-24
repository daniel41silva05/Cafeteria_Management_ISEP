package lapr4.cafeteria.app.backoffice.console.presentation;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import lapr4.cafeteria.app.backoffice.console.presentation.card_accounts.ConsultUserCardBalanceAction;
import lapr4.cafeteria.app.backoffice.console.presentation.card_accounts.ConsultUserCardMovementsAction;
import lapr4.cafeteria.app.backoffice.console.presentation.card_accounts.RechargeUserCardAction;
import lapr4.cafeteria.app.backoffice.console.presentation.dishes.ActivateDeactivateDishAction;
import lapr4.cafeteria.app.backoffice.console.presentation.dishes.ChangeDishAction;
import lapr4.cafeteria.app.backoffice.console.presentation.dishes.ConsultAvailableDishesAction;
import lapr4.cafeteria.app.backoffice.console.presentation.dishes.RegisterDishAction;
import lapr4.cafeteria.app.backoffice.console.presentation.meal_bookings.*;
import lapr4.cafeteria.app.backoffice.console.presentation.meals.ConsultMealsAction;
import lapr4.cafeteria.app.backoffice.console.presentation.meals.RegisterMealAction;
import lapr4.cafeteria.app.backoffice.console.presentation.users.ActivateUserAction;
import lapr4.cafeteria.app.backoffice.console.presentation.users.AddUserAction;
import lapr4.cafeteria.app.backoffice.console.presentation.users.DeactivateUserAction;
import lapr4.cafeteria.app.backoffice.console.presentation.users.ListUsersAction;
import lapr4.cafeteria.app.common.console.presentation.MyUserMenu;
import lapr4.cafeteria.infrastructure.Application;
import lapr4.cafeteria.user_management.domain.CafeteriaRoles;

public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DISABLE_USER_OPTION = 3;
    private static final int ENABLE_USER_OPTION = 4;

    // DISHES
    private static final int REGISTER_DISH_OPTION = 1;
    private static final int ACTIVATE_DEACTIVATE_DISH_OPTION = 2;
    private static final int CHANGE_DISH_OPTION = 3;
    private static final int LIST_DISH_OPTION = 4;

    // MEALS
    private static final int REGISTER_MEAL_OPTION = 1;
    private static final int LIST_MEAL_OPTION = 2;

    // CARD ACCOUNTS
    private static final int CARD_BALANCE_OPTION = 1;
    private static final int RECHARGE_CARD_OPTION = 2;
    private static final int CARD_MOVEMENTS_OPTION = 3;

    // BOOKINGS
    private static final int BOOK_MEAL_OPTION = 1;
    private static final int LIST_MY_BOOKINGS_OPTION = 2;
    private static final int CANCEL_BOOKINGS_OPTION = 3;
    private static final int BOOKING_DELIVERED_OPTION = 4;
    private static final int LIST_BOOKING_OPTION = 5;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int DISHES_OPTION = 3;
    private static final int MEALS_OPTION = 4;
    private static final int CARD_ACCOUNTS_OPTION = 5;
    private static final int BOOKINGS_OPTION = 6;

    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "CAFETERIA [ " + s.authenticatedUser().identity() + " ]")
                .orElse("CAFETERIA [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        final Menu usersMenu = buildUsersMenu();
        mainMenu.addSubMenu(USERS_OPTION, usersMenu);
        final Menu dishesMenu = buildDishesMenu();
        mainMenu.addSubMenu(DISHES_OPTION, dishesMenu);
        final Menu mealsMenu = buildMealsMenu();
        mainMenu.addSubMenu(MEALS_OPTION, mealsMenu);
        final Menu cardAccountsMenu = buildCardAccountsMenu();
        mainMenu.addSubMenu(CARD_ACCOUNTS_OPTION, cardAccountsMenu);
        final Menu bookingsMenu = buildBookingsMenu();
        mainMenu.addSubMenu(BOOKINGS_OPTION, bookingsMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        if (authz.isAuthenticatedUserAuthorizedTo(CafeteriaRoles.POWER_USER, CafeteriaRoles.ADMIN)) {
            menu.addItem(ADD_USER_OPTION, "Add User", new AddUserAction());
            menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
            menu.addItem(DISABLE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
            menu.addItem(ENABLE_USER_OPTION, "Activate User", new ActivateUserAction());
        }

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildDishesMenu() {
        final Menu menu = new Menu("Dishes >");

        if (authz.isAuthenticatedUserAuthorizedTo(CafeteriaRoles.POWER_USER, CafeteriaRoles.MENU_MANAGER)) {
            menu.addItem(REGISTER_DISH_OPTION, "Register Dish", new RegisterDishAction());
            menu.addItem(ACTIVATE_DEACTIVATE_DISH_OPTION, "Activate or Deactivate Dish", new ActivateDeactivateDishAction());
            menu.addItem(CHANGE_DISH_OPTION, "Update Dish Price or Nutritional Info", new ChangeDishAction());
            menu.addItem(LIST_DISH_OPTION, "Consult Available Dishes", new ConsultAvailableDishesAction());
        }

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildMealsMenu() {
        final Menu menu = new Menu("Meals >");

        if (authz.isAuthenticatedUserAuthorizedTo(CafeteriaRoles.POWER_USER, CafeteriaRoles.MENU_MANAGER)) {
            menu.addItem(REGISTER_MEAL_OPTION, "Register Meal", new RegisterMealAction());
            menu.addItem(LIST_MEAL_OPTION, "Consult Meals", new ConsultMealsAction());
        }

        if (authz.isAuthenticatedUserAuthorizedTo(CafeteriaRoles.POWER_USER, CafeteriaRoles.CAFETERIA_USER)) {
            menu.addItem(LIST_MEAL_OPTION, "Consult Meals", new ConsultMealsAction());
        }

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildCardAccountsMenu() {
        final Menu menu = new Menu("Card Accounts >");

        if (authz.isAuthenticatedUserAuthorizedTo(CafeteriaRoles.POWER_USER)) {
            menu.addItem(CARD_BALANCE_OPTION, "Consult User Balance", new ConsultUserCardBalanceAction());
            menu.addItem(RECHARGE_CARD_OPTION, "Recharge User Card", new RechargeUserCardAction());
            menu.addItem(CARD_MOVEMENTS_OPTION, "Consult Card Movements", new ConsultUserCardMovementsAction());
        }
        if (authz.isAuthenticatedUserAuthorizedTo(CafeteriaRoles.CAFETERIA_USER)) {
            menu.addItem(CARD_BALANCE_OPTION, "Consult User Balance", new ConsultUserCardBalanceAction());
            menu.addItem(CARD_MOVEMENTS_OPTION, "Consult Card Movements", new ConsultUserCardMovementsAction());
        }
        if (authz.isAuthenticatedUserAuthorizedTo(CafeteriaRoles.CASHIER)) {
            menu.addItem(RECHARGE_CARD_OPTION, "Recharge User Card", new RechargeUserCardAction());
        }

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildBookingsMenu() {
        final Menu menu = new Menu("Bookings >");

        if (authz.isAuthenticatedUserAuthorizedTo(CafeteriaRoles.POWER_USER, CafeteriaRoles.CAFETERIA_USER)) {
            menu.addItem(BOOK_MEAL_OPTION, "Book a Meal", new BookMealAction());
            menu.addItem(LIST_MY_BOOKINGS_OPTION, "Consult My Bookings", new ConsultMyBookingAction());
            menu.addItem(CANCEL_BOOKINGS_OPTION, "Cancel Booking", new CancelBookingAction());
        }
        if (authz.isAuthenticatedUserAuthorizedTo(CafeteriaRoles.POWER_USER, CafeteriaRoles.CASHIER)) {
            menu.addItem(BOOKING_DELIVERED_OPTION, "Mark Booking as Delivered", new MarkBookingAsDeliveredAction());
        }
        if (authz.isAuthenticatedUserAuthorizedTo(CafeteriaRoles.POWER_USER, CafeteriaRoles.KITCHEN_MANAGER)) {
            menu.addItem(LIST_BOOKING_OPTION, "Consult Booking Meals", new ConsultBookingMealsAction());
        }

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

}
