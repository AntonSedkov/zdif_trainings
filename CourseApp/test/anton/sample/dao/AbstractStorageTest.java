package anton.sample.dao;

import anton.sample.exception.StorageException;
import anton.sample.model.Contact;
import anton.sample.model.ContactType;
import anton.sample.model.Resume;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * User: Sedkov Anton
 * Date: 12.06.2021
 */
abstract public class AbstractStorageTest {
    private Resume r1, r2, r3;
    protected IStorage storage;

    @Before
    public void setUp() throws Exception {
        r1 = new Resume();
        r1.setFullName("Gregory Foe");
        r1.setLocation("Austria");
        r1.addContact(new Contact(ContactType.MAIL, "mail@qq.we"));
        r1.addContact(new Contact(ContactType.PHONE, "123456789"));
        r2 = new Resume();
        r2.setFullName("Antonius Lowery");
        r2.setLocation("China");
        r3 = new Resume();
        r3.setFullName("Bonita Juanita");
        r3.setLocation("Cuba");
    }

    @After
    public void after() {
        storage.clear();
    }

    @Test
    public void testClear() {
        try {
            storage.save(r1);
            storage.clear();
            assertEquals(0, storage.size());
        } catch (StorageException e) {
            fail();
        }
    }

    @Test
    public void testSave() {
        try {
            storage.save(r1);
            Resume expected = storage.load(r1.getUuid());
            assertEquals(r1, expected);
        } catch (StorageException e) {
            fail();
        }
    }

    @Test
    public void testUpdate() {
        try {
            storage.save(r2);
            String newName = "New Name Man";
            r2.setFullName(newName);
            storage.update(r2);
            String actual = storage.load(r2.getUuid()).getFullName();
            assertEquals(newName, actual);
        } catch (StorageException e) {
            fail();
        }
    }

    @Test
    public void testLoad() {
        try {
            storage.save(r1);
            Resume expected = storage.load(r1.getUuid());
            assertEquals(r1, expected);
        } catch (StorageException e) {
            fail();
        }
    }

    @Test(expected = StorageException.class)
    public void testLoadNotFound() throws StorageException {
        storage.save(r1);
        storage.save(r2);
        storage.load("NotExist");
    }

    @Test
    public void testDelete() {
        try {
            storage.save(r1);
            storage.save(r2);
            storage.save(r3);
            storage.delete(r3.getUuid());
            assertEquals(2, storage.size());
        } catch (StorageException e) {
            fail();
        }
    }

    @Test
    public void getAllSorted() {
        try {
            storage.save(r1);
            storage.save(r2);
            storage.save(r3);
            Collection<Resume> actual = storage.getAllSorted();
            Collection<Resume> expected = new ArrayList<>();
            expected.add(r2);
            expected.add(r3);
            expected.add(r1);
            assertEquals(expected, actual);
        } catch (StorageException e) {
            fail();
        }
    }

}
