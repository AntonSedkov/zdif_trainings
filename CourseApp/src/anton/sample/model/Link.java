package anton.sample.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Objects;

/**
 * User: Sedkov Anton
 * Date: 07.06.2021
 */

@XmlAccessorType(XmlAccessType.FIELD)
public record Link(String name, String url) implements Serializable {
    public static final Link EMPTY = new Link();

    public Link() {
        this("", "");
    }

    public Link(Link link) {
        this(link.name, link.url);
    }

    public Link {
        Objects.requireNonNull(name, "name is null");
        this.name = name;
        this.url = url == null ? "" : url;
    }

}
