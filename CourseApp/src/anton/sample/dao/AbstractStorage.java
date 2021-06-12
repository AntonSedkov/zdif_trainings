package anton.sample.dao;

import anton.sample.exception.StorageException;
import anton.sample.model.Resume;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 * User: Sedkov Anton
 * Date: 09.06.2021
 */
public abstract class AbstractStorage<T> implements IStorage {
    protected final Logger logger = Logger.getLogger(getClass().getName());

    protected abstract T getContext(String uuid);

    protected abstract boolean isExist(T uuid);

    @Override
    public void clear() throws StorageException {
        logger.info("Delete all resume.");
        doClear();
    }

    protected abstract void doClear() throws StorageException;

    @Override
    public void save(Resume resume) throws StorageException {
        logger.info("Save resume with uuid=" + resume.getUuid());
        T context = getContext(resume.getUuid());
        if (isExist(context)) {
            throw new StorageException("Resume is already exist: uuid - " + resume.getUuid());
        }
        doSave(context, resume);
    }

    protected abstract void doSave(T context, Resume resume);

    @Override
    public void update(Resume resume) throws StorageException {
        logger.info("Update resume with uuid=" + resume.getUuid());
        T context = getContext(resume.getUuid());
        if (!isExist(context)) {
            throw new StorageException("Resume is not stored: uuid - " + resume.getUuid());
        }
        doUpdate(context, resume);
    }

    protected abstract void doUpdate(T context, Resume resume);

    @Override
    public Resume load(String uuid) throws StorageException {
        logger.info("Load resume w ith uuid=" + uuid);
        T context = getContext(uuid);
        if (!isExist(context)) {
            throw new StorageException("Resume is not found: uuid - " + uuid);
        }
        return doLoad(context);
    }

    protected abstract Resume doLoad(T context);

    @Override
    public void delete(String uuid) throws StorageException {
        logger.info("Delete resume with uuid=" + uuid);
        T context = getContext(uuid);
        if (!isExist(context)) {
            throw new StorageException("Resume is not stored: uuid - " + uuid);
        }
        doDelete(context);
    }

    protected abstract void doDelete(T context) throws StorageException;

    @Override
    public Collection<Resume> getAllSorted() {
        logger.info("Load resumes sorted by names.");
        List<Resume> resumes = doGetAll();
        resumes.sort(Comparator.comparing(Resume::getFullName, Comparator.naturalOrder()));
        return resumes;
    }

    protected abstract List<Resume> doGetAll();

}
