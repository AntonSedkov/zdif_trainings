package anton.sample.dao;

import anton.sample.entity.Advertisement;

import java.util.List;

/**
 * User: Sedkov Anton
 * Date: 17.07.2021
 */
public interface AdvDao {
    List<Advertisement> listAll();

    List<Advertisement> list(String pattern);

    void add(Advertisement adv);

    void delete(long id);

    byte[] getPhoto(long id);
}
