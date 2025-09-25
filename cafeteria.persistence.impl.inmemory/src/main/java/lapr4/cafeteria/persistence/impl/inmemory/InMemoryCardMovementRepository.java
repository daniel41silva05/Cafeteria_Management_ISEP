package lapr4.cafeteria.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import lapr4.cafeteria.card_account.domain.CardMovement;
import lapr4.cafeteria.card_account.repositories.CardMovementRepository;
import lapr4.cafeteria.user_management.domain.MecanographicNumber;

import java.util.Calendar;

public class InMemoryCardMovementRepository extends InMemoryDomainRepository<CardMovement, String>
        implements CardMovementRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<CardMovement> findByMecanographicNumber(MecanographicNumber number) {
        return match(movement -> movement.account().identity().equals(number));
    }

    @Override
    public Iterable<CardMovement> findByMecanographicNumberAndPeriod(
            MecanographicNumber number, Calendar beginDate, Calendar endDate) {

        return match(movement ->
                movement.account().identity().equals(number)
                        && !movement.occurredOn().before(beginDate)
                        && !movement.occurredOn().after(endDate)
        );
    }

}
