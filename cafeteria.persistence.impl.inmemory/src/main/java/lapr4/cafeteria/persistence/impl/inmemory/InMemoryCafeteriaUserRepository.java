package lapr4.cafeteria.persistence.impl.inmemory;

import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;
import lapr4.cafeteria.user_management.domain.MecanographicNumber;
import lapr4.cafeteria.user_management.repositories.CafeteriaUserRepository;

import java.util.Optional;

public class InMemoryCafeteriaUserRepository extends InMemoryDomainRepository<CafeteriaUser, MecanographicNumber>
        implements CafeteriaUserRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<CafeteriaUser> findByUsername(Username name) {
        return matchOne(user -> user.user().username().equals(name));
    }

    @Override
    public Iterable<CafeteriaUser> findAllActive() {
        return match(user -> user.user().isActive());
    }

    @Override
    public Iterable<CafeteriaUser> findAllDisabled() {
        return match(user -> !user.user().isActive());
    }

}
