package anton.sample.util;

import anton.sample.exception.StorageException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.Writer;

/**
 * User: Sedkov Anton
 * Date: 14.06.2021
 */
public class XmlParser {
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public XmlParser(Class... classesToBeBound) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(classesToBeBound);
            marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            unmarshaller = ctx.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException("Jaxb init failed", e);
        }
    }

    public <T> T unmarshall(Reader reader) throws StorageException {
        try {
            return (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new StorageException("Jaxb unmarshall failed", e);
        }
    }

    public void marshall(Object instance, Writer writer) throws StorageException {
        try {
            marshaller.marshal(instance, writer);
        } catch (JAXBException e) {
            throw new StorageException("Jaxb marshal failed", e);
        }
    }
}