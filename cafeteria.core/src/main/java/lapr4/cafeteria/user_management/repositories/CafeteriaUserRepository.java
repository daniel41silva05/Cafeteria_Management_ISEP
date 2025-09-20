package lapr4.cafeteria.user_management.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;
import lapr4.cafeteria.user_management.domain.MecanographicNumber;

import java.util.Optional;

public interface CafeteriaUserRepository extends DomainRepository<MecanographicNumber, CafeteriaUser> {

    Optional<CafeteriaUser> findByUsername(Username name);

    Iterable<CafeteriaUser> findAllActive();

    Iterable<CafeteriaUser> findAllDisabled();

}
