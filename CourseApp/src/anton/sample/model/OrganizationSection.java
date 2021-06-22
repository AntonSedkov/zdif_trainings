package anton.sample.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

/**
 * User: Sedkov Anton
 * Date: 07.06.2021
 */
public class OrganizationSection extends Section implements Serializable {
    static final long serialVersionUID = 1L;

    private List<Organization> organizations;

    public OrganizationSection() {
    }

    public OrganizationSection(Organization... values) {
        this.organizations = new LinkedList<>(Arrays.asList(values));
    }

    public OrganizationSection(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection that = (OrganizationSection) o;

        return organizations != null ? organizations.equals(that.organizations) : that.organizations == null;
    }

    @Override
    public int hashCode() {
        return organizations != null ? organizations.hashCode() : 0;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrganizationSection.class.getSimpleName() + "[", "]")
                .add("organizations=" + organizations)
                .toString();
    }
}
