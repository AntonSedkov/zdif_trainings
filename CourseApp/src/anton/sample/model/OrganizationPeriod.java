package anton.sample.model;

import anton.sample.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * User: Sedkov Anton
 * Date: 07.06.2021
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationPeriod implements Serializable {
    public static final long serialVersionUID = 1L;
    public static final LocalDate NOWADAYS = LocalDate.MAX;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;
    private String position;
    private String content;

    public OrganizationPeriod() {
        this(OrganizationPeriod.NOWADAYS, OrganizationPeriod.NOWADAYS, "", "");
    }

    public OrganizationPeriod(LocalDate startDate, LocalDate endDate, String position, String content) {
        Objects.requireNonNull(startDate, "startDate is null");
        Objects.requireNonNull(position, "position is null");
        this.startDate = startDate;
        this.endDate = endDate == null ? NOWADAYS : endDate;
        this.position = position;
        this.content = content == null ? "" : content;
    }

    public OrganizationPeriod(int startYear, Month startMonth, int endYear, Month endMonth, String position, String content) {
        this(LocalDate.of(startYear, startMonth, 1), LocalDate.of(endYear, endMonth, 1), position, content);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationPeriod that = (OrganizationPeriod) o;

        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        return content != null ? content.equals(that.content) : that.content == null;
    }

    @Override
    public int hashCode() {
        int result = startDate != null ? startDate.hashCode() : 0;
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrganizationPeriod.class.getSimpleName() + "[", "]")
                .add("startDate=" + startDate)
                .add("endDate=" + endDate)
                .add("position='" + position + "'")
                .add("content='" + content + "'")
                .toString();
    }
}
