package anton.sample.dao;

import anton.sample.exception.StorageException;
import anton.sample.model.Resume;

import java.util.Collection;

/**
 * User: Sedkov Anton
 * Date: 08.06.2021
 */
public interface IStorage {

    void clear();

    void save(Resume resume) throws StorageException;

    void update(Resume resume) throws StorageException;

    Resume load(String uuid) throws StorageException;

    void delete(String uuid) throws StorageException;

    Collection<Resume> getAllSorted();

    int size();

}
