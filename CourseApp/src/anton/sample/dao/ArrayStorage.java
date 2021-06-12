package anton.sample.dao;

import anton.sample.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * User: Sedkov Anton
 * Date: 08.06.2021
 */
public class ArrayStorage extends AbstractStorage<Integer> {
    private static final int MAX_SIZE = 100;
    private final Resume[] array = new Resume[MAX_SIZE];
    private int currentSize = 0;

    @Override
    protected Integer getContext(String uuid) {
        int index = 0;
        while (index < MAX_SIZE) {
            if (array[index] == null) {
                break;
            }
            if (array[index].getUuid().equals(uuid)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    protected boolean isExist(Integer index) {
        return index != -1;
    }

    @Override
    public void doClear() {
        Arrays.fill(array, null);
        currentSize = 0;
    }

    @Override
    public void doSave(Integer index, Resume resume) {
        array[currentSize++] = resume;
    }

    @Override
    public void doUpdate(Integer index, Resume resume) {
        array[index] = resume;
    }

    @Override
    public Resume doLoad(Integer index) {
        return array[index];
    }

    @Override
    public void doDelete(Integer index) {
        int newSize = currentSize - 1 - index;
        if (newSize > 0) {
            System.arraycopy(array, index + 1, array, index, newSize);
        }
        array[--currentSize] = null;                                           //for Garbage Collector
    }

    @Override
    public List<Resume> doGetAll() {
        return Arrays.asList(Arrays.copyOf(array, currentSize));
    }

    @Override
    public int size() {
        return currentSize;
    }

}
