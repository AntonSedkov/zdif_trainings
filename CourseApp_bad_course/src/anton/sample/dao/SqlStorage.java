package anton.sample.dao;

import anton.sample.exception.StorageException;
import anton.sample.model.Resume;
import anton.sample.sql.Sql;
import anton.sample.sql.SqlExecutor;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Sedkov Anton
 * Date: 15.07.2021
 */
public class SqlStorage implements IStorage {
    private Sql sql;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        this.sql = new Sql(
                () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword)
        );
    }

    @Override
    public void clear() throws StorageException {
        sql.execute("DELETE * FROM resume");
    }

    @Override
    public void save(Resume resume) throws StorageException {
        sql.execute("INSERT INTO resume (uuid, full_name, location, home_page) VALUES (?, ?, ?, ?)",
                ps -> {
                    ps.setString(1, resume.getUuid());
                    ps.setString(2, resume.getFullName());
                    ps.setString(3, resume.getLocation());
                    ps.setString(4, resume.getHomePage());
                    if (ps.executeUpdate() == 0) {
                        throw new StorageException("Resume " + resume.getUuid() + " is not saved");
                    }
                    return null;
                });
    }

    @Override
    public void update(Resume resume) throws StorageException {
        sql.execute("UPDATE resume SET full_name=?, location=?, home_page=? WHERE r.uuid=?",
                ps -> {
                    ps.setString(1, resume.getFullName());
                    ps.setString(2, resume.getLocation());
                    ps.setString(3, resume.getHomePage());
                    ps.setString(4, resume.getUuid());
                    if (ps.executeUpdate() == 0) {
                        throw new StorageException("Resume " + resume.getUuid() + " is not updated");
                    }
                    return null;
                });
    }

    @Override
    public Resume load(String uuid) throws StorageException {
        Resume resume = sql.execute("Select * FROM resume r WHERE r.uuid=?", st -> {
                    st.setString(1, uuid);
                    ResultSet rs = st.executeQuery();
                    if (!rs.next()) {
                        throw new StorageException("Resume " + uuid + " is not exist");
                    }
                    return createResumeFromResultSet(rs);
                }
        );
        return resume;
    }

    @Override
    public void delete(String uuid) throws StorageException {
        sql.execute("DELETE FROM resume WHERE uuid=?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new StorageException("Resume " + uuid + " is not exist");
            }
            return null;
        });
    }

    @Override
    public Collection<Resume> getAllSorted() throws StorageException {
        Collection<Resume> resumes = sql.execute("Select * FROM resume r ORDER BY full_name", new SqlExecutor<Collection<Resume>>() {
                    @Override
                    public Collection<Resume> execute(PreparedStatement st) throws SQLException, StorageException {
                        ResultSet rs = st.executeQuery();
                        if (!rs.next()) {
                            throw new StorageException("Resume table is empty.");
                        }
                        Collection<Resume> resumes1 = new ArrayList<>();
                        while (rs.next()) {
                            Resume resume = createResumeFromResultSet(rs);
                            resumes1.add(resume);
                        }
                        return resumes1;
                    }
                }
        );
        return resumes;
    }

    @Override
    public int size() throws StorageException {
        int size = sql.execute("SELECT count(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        });
        return size;
    }

    @Override
    public boolean isSectionSupported() {
        return false;
    }

    private Resume createResumeFromResultSet(ResultSet rs) throws SQLException {
        Resume resume = new Resume();
        resume.setUuid(rs.getString("uuid"));
        resume.setFullName(rs.getString("full_name"));
        resume.setLocation(rs.getString("location"));
        resume.setHomePage(rs.getString("home_page"));
        return resume;
    }
}
