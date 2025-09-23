package lapr4.cafeteria.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import lapr4.cafeteria.card_account.domain.CardBalance;
import lapr4.cafeteria.card_account.repositories.CardBalanceRepository;
import lapr4.cafeteria.infrastructure.Application;
import lapr4.cafeteria.user_management.domain.MecanographicNumber;

import java.util.Optional;

class JpaCardBalanceRepository extends JpaAutoTxRepository<CardBalance, String, String>
        implements CardBalanceRepository {

    public JpaCardBalanceRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaCardBalanceRepository(final String puname) {
        super(puname, Application.settings().extendedPersistenceProperties(), "id");
    }

    @Override
    public Optional<CardBalance> findByMecanographicNumber(final MecanographicNumber number) {
        final var query = entityManager().createQuery(
                "SELECT cb FROM CardBalance cb WHERE cb.user.mecanographicNumber = :num",
                CardBalance.class
        );
        query.setParameter("num", number);
        return query.getResultStream().findFirst();
    }

}
