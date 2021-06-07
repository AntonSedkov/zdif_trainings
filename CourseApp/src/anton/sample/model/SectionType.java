package anton.sample.model;

/**
 * User: Sedkov Anton
 * Date: 07.06.2021
 */
public enum SectionType {
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
