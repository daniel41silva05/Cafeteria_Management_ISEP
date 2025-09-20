package lapr4.cafeteria.user_management.application;

import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.domain.model.Username;
import lapr4.cafeteria.infrastructure.persistence.PersistenceContext;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;
import lapr4.cafeteria.user_management.domain.MecanographicNumber;
import lapr4.cafeteria.user_management.repositories.CafeteriaUserRepository;

import java.util.Optional;

@ApplicationService
public class CafeteriaUserService {

    private final CafeteriaUserRepository repo = PersistenceContext.repositories().cafeteriaUsers();

    public Iterable<CafeteriaUser> allUsers() {
        return repo.findAll();
    }

    public Optional<CafeteriaUser> findUserByMecNumber(final String mecanographicNumber) {
        return repo.ofIdentity(MecanographicNumber.valueOf(mecanographicNumber));
    }

    public Optional<CafeteriaUser> findUserByUsername(final Username username) {
        return repo.findByUsername(username);
    }

}
