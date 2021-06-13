package anton.sample.dao;

import anton.sample.exception.StorageException;
import anton.sample.model.ContactType;
import anton.sample.model.Resume;
import anton.sample.model.SectionType;
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
        r1.addContact(ContactType.MAIL, "mail@qq.we");
        r1.addContact(ContactType.PHONE, "123456789");
        r1.addObjective("Objective","Ya");
        r1.addMultiTextSection(SectionType.ACHIEVEMENT, "ach 1", "ACH 2", "AcH3");
        r1.addMultiTextSection(SectionType.QUALIFICATION, "Qua1", "Java");
        //todo exp and edu
        r2 = new Resume();
        r2.setFullName("Antonius Lowery");
        r2.setLocation("China");
        r3 = new Resume();
        r3.setFullName("Bonita Juanita");
        r3.setLocation("Cuba");
    }

    @After
    public void after() {
        try {
            storage.clear();
        } catch (StorageException e) {
            fail();
        }
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


    @Test(expected = StorageException.class)
    public void testSaveExisted() throws StorageException {
        storage.save(r1);
        storage.save(r2);
        storage.save(r1);
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

    @Test(expected = StorageException.class)
    public void testUpdateNotExisted() throws StorageException {
        storage.save(r1);
        storage.save(r2);
        Resume notExist = new Resume();
        storage.update(notExist);
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

    @Test(expected = StorageException.class)
    public void testDeleteNotExisted() throws StorageException {
        storage.save(r1);
        storage.save(r2);
        storage.delete("NotExist");
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

    @Test
    public void testSize() {
        try {
            storage.save(r1);
            storage.save(r2);
            storage.save(r3);
            assertEquals(3, storage.size());
        } catch (StorageException e) {
            fail();
        }
    }

}
