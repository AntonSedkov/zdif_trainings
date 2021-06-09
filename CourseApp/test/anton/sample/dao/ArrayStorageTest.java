package anton.sample.dao;

import anton.sample.exception.StorageException;
import anton.sample.model.Resume;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Sedkov Anton
 * Date: 08.06.2021
 */
public class ArrayStorageTest {
    private IStorage storage;
    private Resume r1, r2, r3;

    @Before
    public void before() {
        storage = new ArrayStorage();
        r1 = new Resume();
        r1.setFullName("Gregory Foe");
        r1.setLocation("Austria");
        r2 = new Resume();
        r2.setFullName("Antonius Lowery");
        r2.setLocation("China");
        r3 = new Resume();
        r3.setFullName("Bonita Juanita");
        r3.setLocation("Cuba");
    }

    @After
    public void after() {
        storage = null;
        r1 = null;
        r2 = null;
        r3 = null;
    }

    @Test
    public void clear() {
        storage.clear();
        int size = storage.size();
        Assert.assertEquals(0, size);
    }

    @Test
    public void save() throws StorageException {
        storage.save(r1);
        int size = storage.size();
        Assert.assertEquals(1, size);
    }

    @Test
    public void update() throws StorageException {
        storage.save(r1);
        String newName = "Olive Juvenal";
        r1.setFullName(newName);
        storage.update(r1);
        String name = storage.load(r1.getUuid()).getFullName();
        Assert.assertEquals(newName, name);
    }

    @Test
    public void load() throws StorageException {
        storage.save(r1);
        Resume resume = storage.load(r1.getUuid());
        Assert.assertEquals(r1, resume);
    }

    @Test
    public void delete() throws StorageException {
        storage.save(r1);
        storage.delete(r1.getUuid());
        int size = storage.size();
        Assert.assertEquals(0, size);
    }

    @Test
    public void getAllSorted() throws StorageException {
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
        Collection<Resume> actual = storage.getAllSorted();
        Collection<Resume> expected = new ArrayList<>();
        expected.add(r2);
        expected.add(r3);
        expected.add(r1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void size() throws StorageException {
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
        int actual = storage.size();
        Assert.assertEquals(3, actual);
    }
}