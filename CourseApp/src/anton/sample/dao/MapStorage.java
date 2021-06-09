package anton.sample.dao;

import anton.sample.exception.StorageException;
import anton.sample.model.Resume;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Sedkov Anton
 * Date: 09.06.2021
 */
public class MapStorage implements IStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void save(Resume resume) throws StorageException {
        storage.putIfAbsent(resume.getUuid(), resume);
    }

    @Override
    public void update(Resume resume) throws StorageException {
        String uuid = resume.getUuid();
        if (storage.containsKey(uuid)) {
            storage.put(uuid, resume);
            return;
        }
        throw new StorageException();
    }

    @Override
    public Resume load(String uuid) throws StorageException {
        return null;
    }

    @Override
    public void delete(String uuid) throws StorageException {

    }

    @Override
    public Collection<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
