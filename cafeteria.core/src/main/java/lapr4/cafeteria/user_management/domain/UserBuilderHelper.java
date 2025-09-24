package lapr4.cafeteria.user_management.domain;

import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.util.Utility;

@Utility
public class UserBuilderHelper {
	private UserBuilderHelper() {
		// ensure utility
	}

	public static SystemUserBuilder builder() {
		return new SystemUserBuilder(new CafeteriaPasswordPolicy(), new PlainTextEncoder());
	}

}
