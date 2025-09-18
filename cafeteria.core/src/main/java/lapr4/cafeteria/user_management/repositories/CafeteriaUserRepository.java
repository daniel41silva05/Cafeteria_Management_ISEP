package lapr4.cafeteria.user_management.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import lapr4.cafeteria.user_management.domain.CafeteriaUser;
import lapr4.cafeteria.user_management.domain.MecanographicNumber;

public interface CafeteriaUserRepository extends DomainRepository<MecanographicNumber, CafeteriaUser> {

    Iterable<CafeteriaUser> findAllActive();

    Iterable<CafeteriaUser> findAllDisabled();

}
