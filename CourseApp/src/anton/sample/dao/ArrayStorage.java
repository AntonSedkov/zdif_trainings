package anton.sample.dao;

import anton.sample.exception.StorageException;
import anton.sample.model.Resume;

import java.util.*;
import java.util.logging.Logger;

/**
 * User: Sedkov Anton
 * Date: 08.06.2021
 */
public class ArrayStorage extends AbstractStorage {
    private static final int SIZE = 100;
    private Resume[] array = new Resume[SIZE];

    @Override
    protected boolean isExist(String uuid) {
        for (int i = 0; i < SIZE; i++) {
            if (array[i].getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void doClear() {
        Arrays.fill(array, null);
    }

    @Override
    public void doSave(Resume resume) {
        for (int i = 0; i < SIZE; i++) {
            if (array[i] == null) {
                array[i] = resume;
            }
        }
    }

    @Override
    public void doUpdate(Resume resume) {
        String idx = resume.getUuid();
        for (int i = 0; i < SIZE; i++) {
            if (array[i].getUuid().equals(idx)) {
                array[i] = resume;
                return;
            }
        }
    }

    @Override
    public Resume doLoad(String uuid) {
        for (int i = 0; i < SIZE; i++) {
            if (array[i].getUuid().equals(uuid)) {
                return array[i];
            }
        }
        return null;
    }

    @Override
    public void doDelete(String uuid) {
        for (int i = 0; i < SIZE; i++) {
            if (array[i].getUuid().equals(uuid)) {
                array[i] = null;
                System.arraycopy(array, i + 1, array, i, SIZE - 1 - i);
                return;
            }
        }
    }

    @Override
    public Collection<Resume> doGetAll() {
        List<Resume> resumes = new ArrayList<>();
        for (Resume resume : array) {
            if (resume != null) {
                resumes.add(resume);
            } else {
                break;
            }
        }
        resumes.sort(Comparator.comparing(Resume::getFullName, Comparator.naturalOrder()));
        return resumes;
    }

    @Override
    public int size() {
        int size = 0;
        for (Resume resume : array) {
            if (resume != null) {
                size++;
            }
        }
        return size;
    }

}
