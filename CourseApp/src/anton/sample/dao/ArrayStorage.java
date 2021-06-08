package anton.sample.dao;

import anton.sample.model.Resume;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * User: Sedkov Anton
 * Date: 08.06.2021
 */
public class ArrayStorage implements IStorage {
    private static final int SIZE = 100;
    private Resume[] array = new Resume[SIZE];

    @Override
    public void clear() {
        array = new Resume[SIZE];
    }

    @Override
    public void save(Resume resume) {
        int idx = -1;
        for (int i = 0; i < SIZE; i++) {
            Resume resumeOld = array[i];
            if (resumeOld != null) {
                if (resume.equals(resumeOld)) {
                    throw new IllegalStateException("Already present");
                }
            } else if (idx == -1) {
                idx = i;
            }
        }
        array[idx] = resume;
    }

    @Override
    public void update(Resume resume) {
        for (int i = 0; i < SIZE; i++) {
            if (array[i].equals(resume)) {
                array[i] = resume;
                return;
            }
        }
        throw new IllegalStateException("Resume is not stored");
    }

    @Override
    public Resume load(String uuid) {
        Resume resume;
        for (int i = 0; i < SIZE; i++) {
            if (array[i].getUuid().equals(uuid)) {
                resume = array[i];
                return resume;
            }
        }
        throw new IllegalStateException("Resume is not found");
    }

    @Override
    public void delete(String uuid) {
        for (int i = 0; i < SIZE; i++) {
            if (array[i].getUuid().equals(uuid)) {
                array[i] = null;
                return;
            }
        }
        throw new IllegalStateException("Resume is not stored");
    }

    @Override
    public Collection<Resume> getAllSorted() {
        List<Resume> resumes = new ArrayList<>();
        for (Resume resume : array) {
            if (resume != null) {
                resumes.add(resume);
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
