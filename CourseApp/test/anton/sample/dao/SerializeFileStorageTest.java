package anton.sample.dao;

/**
 * User: Sedkov Anton
 * Date: 13.06.2021
 */
public class SerializeFileStorageTest extends AbstractStorageTest {
    {
        storage = new SerializeFileStorage("./file_storage");
    }
}