package lapr4.cafeteria.infrastructure;

import eapli.framework.infrastructure.authz.domain.model.Role;

public interface CredentialHandler {
	boolean authenticated(String username, String password, Role onlyWithThis);
}
