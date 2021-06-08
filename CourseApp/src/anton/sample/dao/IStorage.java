package anton.sample.dao;

import anton.sample.model.Resume;

import java.util.Collection;

/**
 * User: Sedkov Anton
 * Date: 08.06.2021
 */
public interface IStorage {

    void clear();

    void save(Resume resume);

    void update(Resume resume);

    Resume load(String uuid);

    void delete(String uuid);

    Collection<Resume> getAllSorted();

    int size();

}
