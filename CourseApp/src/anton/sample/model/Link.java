package anton.sample.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * User: Sedkov Anton
 * Date: 07.06.2021
 */

@XmlAccessorType(XmlAccessType.FIELD)
public record Link(String name, String url) implements Serializable {
}
