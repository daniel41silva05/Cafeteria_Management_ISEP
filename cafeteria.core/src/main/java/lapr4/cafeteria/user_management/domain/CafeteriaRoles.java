package lapr4.cafeteria.user_management.domain;

import eapli.framework.infrastructure.authz.domain.model.Role;

public final class CafeteriaRoles {

    /**
     * System poweruser (this is a technical role).
     */
    public static final Role POWER_USER = Role.valueOf("POWER_USER");

    /**
     * System Administrator.
     */
    public static final Role ADMIN = Role.valueOf("ADMIN");

    /**
     * Kitchen manager.
     */
    public static final Role KITCHEN_MANAGER = Role.valueOf("KITCHEN_MANAGER");

    /**
     * Responsible for planning menus.
     */
    public static final Role MENU_MANAGER = Role.valueOf("MENU_MANAGER");

    /**
     * Responsible for delivering meals and charging user cards.
     */
    public static final Role CASHIER = Role.valueOf("CASHIER");

    /**
     * User.
     */
    public static final Role CAFETERIA_USER = Role.valueOf("CAFETERIA_USER");


    public static Role[] allRoles() {
        return new Role[] {
                ADMIN,
                KITCHEN_MANAGER,
                MENU_MANAGER,
                CASHIER,
                CAFETERIA_USER
        };
    }

    public static boolean isRecognizedRole(final Role role) {
        for (Role r : allRoles()) {
            if (r.equals(role)) return true;
        }
        return false;
    }

}