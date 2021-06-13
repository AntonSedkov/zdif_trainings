package anton.sample.model;

import java.io.Serializable;

/**
 * User: Sedkov Anton
 * Date: 07.06.2021
 */
public record Link(String name, String url) implements Serializable {
}
