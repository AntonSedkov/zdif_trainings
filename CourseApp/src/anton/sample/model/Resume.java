package anton.sample.model;

import java.util.List;
import java.util.UUID;

/**
 * User: Sedkov Anton
 * Date: 06.06.2021
 */

public class Resume {
    private final String uuid;
    private String fullName;
    private String location;
    private String homePage;
    private List<Contact> contacts;
    private List<Section> sections;

    public Resume() {
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public boolean add(Contact contact) {
        return contacts.add(contact);
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Section> getSections() {
        return sections;
    }

    public boolean add(Section section) {
        return sections.add(section);
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}