package lapr4.cafeteria.persistence.impl.inmemory;

import lapr4.cafeteria.infrastructure.bootstrapers.CafeteriaBootstrapper;
import lapr4.cafeteria.infrastructure.bootstrapers.demo.CafeteriaDemoBootstrapper;

final class InMemoryInitializer {

	private static class LazyHolder {
		private static final InMemoryInitializer INSTANCE = new InMemoryInitializer();

		private LazyHolder() {
		}
	}

	private boolean initialized;

	private InMemoryInitializer() {
	}

	private synchronized void initialize() {
		if (!initialized) {
			new CafeteriaBootstrapper().execute();
			new CafeteriaDemoBootstrapper().execute();
			initialized = true;
		}
	}

	public static void init() {
		LazyHolder.INSTANCE.initialize();
	}
}
