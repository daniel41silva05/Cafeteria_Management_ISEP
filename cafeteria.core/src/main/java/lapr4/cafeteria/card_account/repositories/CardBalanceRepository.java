package lapr4.cafeteria.card_account.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import lapr4.cafeteria.card_account.domain.CardBalance;
import lapr4.cafeteria.user_management.domain.MecanographicNumber;

import java.util.Optional;

public interface CardBalanceRepository extends DomainRepository<String, CardBalance> {

    Optional<CardBalance> findByMecanographicNumber(MecanographicNumber number);

}
