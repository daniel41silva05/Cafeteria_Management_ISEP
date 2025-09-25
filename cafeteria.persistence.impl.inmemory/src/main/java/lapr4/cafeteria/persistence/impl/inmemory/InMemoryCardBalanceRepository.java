package lapr4.cafeteria.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import lapr4.cafeteria.card_account.domain.CardBalance;
import lapr4.cafeteria.card_account.repositories.CardBalanceRepository;
import lapr4.cafeteria.user_management.domain.MecanographicNumber;

import java.util.Optional;

public class InMemoryCardBalanceRepository extends InMemoryDomainRepository<CardBalance, String>
        implements CardBalanceRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<CardBalance> findByMecanographicNumber(MecanographicNumber number) {
        return matchOne(cb -> cb.user().identity().equals(number));
    }

}
