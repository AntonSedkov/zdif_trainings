package anton.sample;

import anton.sample.dao.IStorage;
import anton.sample.dao.XmlFileStorage;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

/**
 * User: Sedkov Anton
 * Date: 23.06.2021
 */
public class WebAppConfig {
    private static final WebAppConfig INSTANCE = new WebAppConfig();

    private IStorage storage;

    public static WebAppConfig getInstance() {
        return INSTANCE;
    }

    public IStorage getStorage() {
        return storage;
    }

    private WebAppConfig() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("logging.properties")) {
            LogManager.getLogManager().readConfiguration(is);
            storage = new XmlFileStorage("E:\\github\\zdif_trainings\\CourseApp\\file_storage");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
