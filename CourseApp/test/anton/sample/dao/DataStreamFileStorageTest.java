package anton.sample.dao;

/**
 * User: Sedkov Anton
 * Date: 13.06.2021
 */
public class DataStreamFileStorageTest extends AbstractStorageTest {
    {
        storage = new DataStreamFileStorage("./file_storage");
    }
}