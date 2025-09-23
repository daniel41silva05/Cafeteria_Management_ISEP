package lapr4.cafeteria.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import lapr4.cafeteria.card_account.domain.CardMovement;
import lapr4.cafeteria.card_account.repositories.CardMovementRepository;
import lapr4.cafeteria.infrastructure.Application;
import lapr4.cafeteria.user_management.domain.MecanographicNumber;

import java.util.Calendar;

class JpaCardMovementRepository extends JpaAutoTxRepository<CardMovement, String, String>
        implements CardMovementRepository {

    public JpaCardMovementRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaCardMovementRepository(final String puname) {
        super(puname, Application.settings().extendedPersistenceProperties(), "id");
    }

    @Override
    public Iterable<CardMovement> findByMecanographicNumber(final MecanographicNumber number) {
        final var query = entityManager().createQuery(
                "SELECT cm FROM CardMovement cm WHERE cm.user.mecanographicNumber = :num ORDER BY cm.occurredOn DESC",
                CardMovement.class
        );
        query.setParameter("num", number);
        return query.getResultList();
    }

    @Override
    public Iterable<CardMovement> findByMecanographicNumberAndPeriod(final MecanographicNumber number,
                                                                     final Calendar beginDate,
                                                                     final Calendar endDate) {
        final var query = entityManager().createQuery(
                "SELECT cm FROM CardMovement cm " +
                        "WHERE cm.user.mecanographicNumber = :num " +
                        "AND cm.occurredOn BETWEEN :begin AND :end " +
                        "ORDER BY cm.occurredOn DESC",
                CardMovement.class
        );
        query.setParameter("num", number);
        query.setParameter("begin", beginDate);
        query.setParameter("end", endDate);
        return query.getResultList();
    }

}
