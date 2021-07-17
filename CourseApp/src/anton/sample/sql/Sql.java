package anton.sample.sql;

import anton.sample.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * User: Sedkov Anton
 * Date: 17.07.2021
 */
public class Sql {
    private final ConnectionFactory connectionFactory;

    public Sql(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sql) throws StorageException {
        execute(sql, ps -> {
            ps.execute();
            return null;
        });
    }

    public <T> T execute(String sql, SqlExecutor<T> executor) throws StorageException {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            throw new StorageException("SQL failed: " + sql, e);
        }
    }

}
