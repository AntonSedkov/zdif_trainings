package anton.sample.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

/**
 * User: Sedkov Anton
 * Date: 06.06.2021
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Serializable {
    static final long serialVersionUID = 1L;

    private String uuid;
    private String fullName;
    private String location;
    private String homePage;
    private Map<ContactType, String> contacts;
    private Map<SectionType, Section> sections;

    public static final Resume EMPTY_RESUME;

    static {
        EMPTY_RESUME = new Resume();
        for (SectionType type : SectionType.values()) {
/*
            EMPTY_RESUME.addSection(type,type.getSectionClass().getEmptySection());
*/
        }
    }

    public Resume() {
        this.uuid = UUID.randomUUID().toString();
        fullName = "";
        location = "";
        homePage = "";
        contacts = new EnumMap<>(ContactType.class);
        sections = new EnumMap<>(SectionType.class);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        Objects.requireNonNull(uuid, "uuid is null");
        this.uuid = uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        Objects.requireNonNull(fullName, "fullName is null");
        this.fullName = fullName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        Objects.requireNonNull(location, "location is null");
        this.location = location;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        Objects.requireNonNull(homePage, "homePage is null");
        this.homePage = homePage;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public void addContact(ContactType type, String contact) {
        contacts.put(type, contact);
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    public Section getSection(SectionType type) {
        return sections.get(type);
    }

    public void addSection(SectionType type, Section section) {
        sections.put(type, section);
    }

    public void addObjective(String title, String comment) {
        addSection(SectionType.OBJECTIVE, new TextWithTitleSection(title, comment));
    }

    public void addMultiTextSection(SectionType type, String... values) {
        addSection(type, new MultiTextSection(Arrays.asList(values)));
    }

    public void addOrganizationSection(SectionType type, Organization... organizations) {
        addSection(type, new OrganizationSection(organizations));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (uuid != null ? !uuid.equals(resume.uuid) : resume.uuid != null) return false;
        if (fullName != null ? !fullName.equals(resume.fullName) : resume.fullName != null) return false;
        if (location != null ? !location.equals(resume.location) : resume.location != null) return false;
        if (homePage != null ? !homePage.equals(resume.homePage) : resume.homePage != null) return false;
        if (contacts != null ? !contacts.equals(resume.contacts) : resume.contacts != null) return false;
        return sections != null ? sections.equals(resume.sections) : resume.sections == null;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (homePage != null ? homePage.hashCode() : 0);
        result = 31 * result + (contacts != null ? contacts.hashCode() : 0);
        result = 31 * result + (sections != null ? sections.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Resume.class.getSimpleName() + "[", "]")
                .add("uuid='" + uuid + "'")
                .add("fullName='" + fullName + "'")
                .add("location='" + location + "'")
                .add("homePage='" + homePage + "'")
                .add("contacts=" + contacts)
                .add("sections=" + sections)
                .toString();
    }
}