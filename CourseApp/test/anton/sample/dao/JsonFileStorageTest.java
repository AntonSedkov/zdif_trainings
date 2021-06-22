package anton.sample.dao;

/**
 * User: Sedkov Anton
 * Date: 22.06.2021
 */
public class JsonFileStorageTest extends AbstractStorageTest {

    {
        storage = new JsonFileStorage("./file_storage");
    }

}