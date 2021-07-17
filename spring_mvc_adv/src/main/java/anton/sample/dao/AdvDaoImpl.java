package anton.sample.dao;

import anton.sample.entity.Advertisement;
import anton.sample.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * User: Sedkov Anton
 * Date: 17.07.2021
 */
public class AdvDaoImpl implements AdvDao {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Advertisement> listAll() {
        Query query = entityManager.createQuery("SELECT a FROM Advertisement a", Advertisement.class);
        return (List<Advertisement>) query.getResultList();
    }

    @Override
    public List<Advertisement> list(String pattern) {
        Query query = entityManager.createQuery(
                "SELECT a FROM Advertisement a WHERE a.shortDesc LIKE :pattern",
                Advertisement.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return (List<Advertisement>) query.getResultList();
    }

    @Override
    public void add(Advertisement adv) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(adv);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try {
            entityManager.getTransaction().begin();
            Advertisement adv = entityManager.find(Advertisement.class, id);
            entityManager.remove(adv);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public byte[] getPhoto(long id) {
        try {
            Photo photo = entityManager.find(Photo.class, id);
            return photo.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
