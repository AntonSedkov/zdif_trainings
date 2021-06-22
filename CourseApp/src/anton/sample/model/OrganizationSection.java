package anton.sample.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        this.organizations = new ArrayList<>(Arrays.asList(values));
    }
}
