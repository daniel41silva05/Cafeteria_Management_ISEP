package lapr4.cafeteria.persistence.impl.jpa;

import eapli.framework.infrastructure.authz.domain.model.Username;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;
import lapr4.cafeteria.user_management.domain.MecanographicNumber;
import lapr4.cafeteria.user_management.repositories.CafeteriaUserRepository;

import java.util.Optional;

class JpaCafeteriaUserRepository extends CafeteriaJpaRepositoryBase<CafeteriaUser, Long, MecanographicNumber>
        implements CafeteriaUserRepository {

    public JpaCafeteriaUserRepository() {
        super("mecanographicNumber");
    }

    @Override
    public Optional<CafeteriaUser> findByUsername(final Username name) {
        return matchOne("e.systemUser.username = :uname", "uname", name);
    }

    @Override
    public Iterable<CafeteriaUser> findAllActive() {
        return match("e.systemUser.active = true");
    }

    @Override
    public Iterable<CafeteriaUser> findAllDisabled() {
        return match("e.systemUser.active = false");
    }

}