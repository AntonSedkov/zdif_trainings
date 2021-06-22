package anton.sample.dao;

/**
 * User: Sedkov Anton
 * Date: 14.06.2021
 *
 * Need for Java 14 (JAXB excluded):
 * jakarta.xml.bind:jakarta.xml.bind-api:2.3.2
 * org.glassfish.jaxb:jaxb-runtime:2.3.2
 *
 */
public class XmlFileStorageTest extends AbstractStorageTest {

    {
        storage = new XmlFileStorage("./file_storage");
    }
}