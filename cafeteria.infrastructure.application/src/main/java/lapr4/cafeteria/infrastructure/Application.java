package lapr4.cafeteria.infrastructure;

import eapli.framework.util.Utility;

@Utility
public class Application {

	public static final String VERSION = "v1.0 - Cafeteria Management System";
	public static final String COPYRIGHT = "(C) 2025, Daniel Silva";

	private static final AppSettings SETTINGS = new AppSettings();

	public static AppSettings settings() {
		return SETTINGS;
	}

	private Application() {
		// private visibility to ensure singleton & utility
	}
}
