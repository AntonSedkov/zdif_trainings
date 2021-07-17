package anton.sample.model;

import java.io.Serializable;

/**
 * User: Sedkov Anton
 * Date: 07.06.2021
 */
public enum SectionType implements Serializable {
    OBJECTIVE("Objective"),
    ACHIEVEMENT("Achievement"),
    QUALIFICATION("Qualification"),
    EXPERIENCE("Experience"),
    EDUCATION("Education");

    private final String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
