package lapr4.cafeteria.infrastructure.smoketests;

import eapli.framework.actions.Action;
import eapli.framework.actions.ChainedAction;

@SuppressWarnings({ "squid:S1126", "java:S106" })
public class CafeteriaDemoSmokeTester implements Action {

	@Override
	public boolean execute() {
		System.out.println("\n\n------- SPECIFIC FEATURES -------");

		return ChainedAction.first(null).execute();
	}
}
