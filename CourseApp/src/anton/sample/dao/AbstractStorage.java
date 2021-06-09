package anton.sample.dao;

import anton.sample.exception.StorageException;
import anton.sample.model.Resume;

import java.util.Collection;
import java.util.logging.Logger;

/**
 * User: Sedkov Anton
 * Date: 09.06.2021
 */
public abstract class AbstractStorage implements IStorage {
    protected final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void clear() {
        logger.info("Delete all resume.");
        doClear();
    }

    protected abstract void doClear();

    protected abstract boolean isExist(String uuid);

    @Override
    public void save(Resume resume) throws StorageException {
        logger.info("Save resume with uuid=" + resume.getUuid());
        if (isExist(resume.getUuid())) {
            throw new StorageException("Resume is already exist: uuid - " + resume.getUuid());
        }
        doSave(resume);
    }

    protected abstract void doSave(Resume resume);

    @Override
    public void update(Resume resume) throws StorageException {
        logger.info("Update resume with uuid=" + resume.getUuid());
        if (!isExist(resume.getUuid())) {
            throw new StorageException("Resume is not stored: uuid - " + resume.getUuid());
        }
        doUpdate(resume);
    }

    protected abstract void doUpdate(Resume resume);

    @Override
    public Resume load(String uuid) throws StorageException {
        logger.info("Load resume with uuid=" + uuid);
        if (!isExist(uuid)) {
            throw new StorageException("Resume is not found: uuid - " + uuid);
        }
        return doLoad(uuid);
    }

    protected abstract Resume doLoad(String uuid);

    @Override
    public void delete(String uuid) throws StorageException {
        logger.info("Delete resume with uuid=" + uuid);
        if (!isExist(uuid)) {
            throw new StorageException("Resume is not stored: uuid - " + uuid);
        }
        doDelete(uuid);
    }

    protected abstract void doDelete(String uuid);

    @Override
    public Collection<Resume> getAllSorted() {
        logger.info("Load resumes sorted by names.");
        return doGetAll();
    }

    protected abstract Collection<Resume> doGetAll();

}
