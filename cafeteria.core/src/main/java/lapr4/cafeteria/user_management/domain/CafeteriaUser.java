package lapr4.cafeteria.user_management.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;

import java.io.Serial;

@Entity
public class CafeteriaUser implements AggregateRoot<MecanographicNumber> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    @Version
    private Long version;

    @Column(unique = true)
    private MecanographicNumber mecanographicNumber;

    @OneToOne
    private SystemUser systemUser;

    public CafeteriaUser(final SystemUser user, final MecanographicNumber mecanographicNumber) {
        Preconditions.noneNull(user, mecanographicNumber);

        this.systemUser = user;
        this.mecanographicNumber = mecanographicNumber;
    }

    protected CafeteriaUser() {
        // for ORM only
    }

    public SystemUser user() {
        return this.systemUser;
    }

    @Override
    public MecanographicNumber identity() {
        return mecanographicNumber;
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

}
