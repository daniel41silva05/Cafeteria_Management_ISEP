package lapr4.cafeteria.card_account.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import lapr4.cafeteria.card_account.domain.CardMovement;
import lapr4.cafeteria.user_management.domain.MecanographicNumber;

import java.util.Calendar;

public interface CardMovementRepository extends DomainRepository<String, CardMovement> {

    Iterable<CardMovement> findByMecanographicNumber(MecanographicNumber number);

    Iterable<CardMovement> findByMecanographicNumberAndPeriod(MecanographicNumber number, Calendar beginDate, Calendar endDate);

}
