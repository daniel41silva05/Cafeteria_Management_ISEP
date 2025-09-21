package lapr4.cafeteria.card_account.application;

import eapli.framework.application.ApplicationService;
import lapr4.cafeteria.card_account.domain.CardBalance;
import lapr4.cafeteria.card_account.repositories.CardBalanceRepository;
import lapr4.cafeteria.infrastructure.persistence.PersistenceContext;
import lapr4.cafeteria.user_management.domain.MecanographicNumber;

import java.util.Optional;

@ApplicationService
public class CardBalanceService {

    private final CardBalanceRepository repo = PersistenceContext.repositories().cardsBalance();

    public Optional<CardBalance> findCardBalanceByMecNumber(final String mecanographicNumber) {
        return repo.findByMecanographicNumber(MecanographicNumber.valueOf(mecanographicNumber));
    }

    public Optional<CardBalance> findCardBalanceByMecNumber(final MecanographicNumber mecanographicNumber) {
        return repo.findByMecanographicNumber(mecanographicNumber);
    }

}
