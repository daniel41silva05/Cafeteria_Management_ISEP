package lapr4.cafeteria.infrastructure.bootstrapers;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import lapr4.cafeteria.user_management.application.AddUserController;
import lapr4.cafeteria.user_management.application.CafeteriaUserService;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class UsersBootstrapperBase {
    private static final Logger LOGGER = LogManager.getLogger(UsersBootstrapperBase.class);

    private final AddUserController userController = new AddUserController();
    private final CafeteriaUserService userService = new CafeteriaUserService();

    public UsersBootstrapperBase() {
        super();
    }

    protected CafeteriaUser registerUser(final String mecanographicNumber, final String email, final String password,
                                         final String firstName, final String lastName, final Set<Role> roles) {
        CafeteriaUser u = null;
        try {
            u = userController.addUser(mecanographicNumber, email, password, firstName, lastName, roles);
            LOGGER.debug("»»» {}", mecanographicNumber);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // assuming it is just a primary key violation due to the tentative
            // of inserting a duplicated user. let's just lookup that user
            u = userService.findUserByMecNumber(mecanographicNumber).orElseThrow(() -> e);
        }
        return u;
    }

}
