package anton.sample.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * User: Sedkov Anton
 * Date: 17.07.2021
 */
public interface ConnectionFactory {
    Connection getConnection() throws SQLException;



}
