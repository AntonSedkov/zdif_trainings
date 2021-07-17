package anton.sample.sql;

import anton.sample.exception.StorageException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * User: Sedkov Anton
 * Date: 17.07.2021
 */
public interface SqlExecutor<T> {
    T execute(PreparedStatement st) throws SQLException, StorageException;
}
