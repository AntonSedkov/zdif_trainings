package anton.sample.model;

import java.util.Date;

/**
 * User: Sedkov Anton
 * Date: 07.06.2021
 */
public record OrganizationPeriod(Date startDate, Date endDate, String position, String content) {
}
