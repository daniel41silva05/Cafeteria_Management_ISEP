package lapr4.cafeteria.infrastructure.persistence;

import eapli.framework.util.Utility;
import lapr4.cafeteria.infrastructure.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

@Utility
public final class PersistenceContext {
    private static final Logger LOGGER = LogManager.getLogger(PersistenceContext.class);
    private static RepositoryFactory theFactory;

    private PersistenceContext() {
        // ensure utility
    }

    /**
     * Returns the abstract repository factory configured in the application
     * settings
     *
     * @return the repository factory
     */
    public static RepositoryFactory repositories() {
        if (theFactory == null) {
            final String factoryClassName = Application.settings().repositoryFactory();
            try {
                theFactory = (RepositoryFactory) Class.forName(factoryClassName).getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IllegalArgumentException
                     | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                LOGGER.error("Unable to dynamically load the Repository Factory", ex);
                throw new IllegalStateException(
                        "Unable to dynamically load the Repository Factory: " + factoryClassName, ex);
            }
        }
        return theFactory;
    }
}
