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
public class Organization implements Serializable {
    static final long serialVersionUID = 1L;

    private Link link;
    private List<OrganizationPeriod> periods;

    public Organization() {
        link = Link.EMPTY;
        periods = new LinkedList<>();
    }

    public Organization(String linkName, String linkUrl, OrganizationPeriod... periods) {
        this(new Link(linkName, linkUrl), new LinkedList<>(Arrays.asList(periods)));
    }

    public Organization(Link link, List<OrganizationPeriod> periods) {
        this.link = link;
        this.periods = periods;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public List<OrganizationPeriod> getPeriods() {
        return periods;
    }

    public void setPeriods(List<OrganizationPeriod> periods) {
        this.periods = periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        return periods != null ? periods.equals(that.periods) : that.periods == null;
    }

    @Override
    public int hashCode() {
        int result = link != null ? link.hashCode() : 0;
        result = 31 * result + (periods != null ? periods.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Organization.class.getSimpleName() + "[", "]")
                .add("link=" + link)
                .add("periods=" + periods)
                .toString();
    }
}
