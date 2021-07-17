package anton.sample.dao;

import anton.sample.WebAppConfig;

/**
 * User: Sedkov Anton
 * Date: 17.07.2021
 */
public class SqlStorageTest extends AbstractStorageTest {
    {
        storage = WebAppConfig.getInstance().getStorage();
    }
}