package lapr4.cafeteria.persistence.impl.jpa;

import eapli.framework.infrastructure.repositories.impl.jpa.JpaTransactionalRepository;
import lapr4.cafeteria.infrastructure.Application;

/* package */ class CafeteriaJpaRepositoryBase<T, K, I>
        extends JpaTransactionalRepository<T, K, I> {

    CafeteriaJpaRepositoryBase(final String persistenceUnitName, final String identityFieldName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(),
                identityFieldName);
    }

    CafeteriaJpaRepositoryBase(final String identityFieldName) {
        super(Application.settings().persistenceUnitName(),
                Application.settings().extendedPersistenceProperties(), identityFieldName);
    }

}
