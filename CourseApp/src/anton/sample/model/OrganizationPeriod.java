package anton.sample.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

/**
 * User: Sedkov Anton
 * Date: 07.06.2021
 */

@XmlAccessorType(XmlAccessType.FIELD)
public record OrganizationPeriod(LocalDate startDate, LocalDate endDate, String position,
                                 String content) implements Serializable {
    public static final long serialVersionUID = 1L;
    public static final LocalDate NOWADAYS = LocalDate.MAX;

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

}
