package anton.sample.dao;

import anton.sample.exception.StorageException;
import anton.sample.model.Resume;

import java.io.*;

/**
 * User: Sedkov Anton
 * Date: 12.06.2021
 */
public class SerializeFileStorage extends FileStorage {

    public SerializeFileStorage(String path) {
        super(path);
    }

    protected void writeFile(File file, Resume resume) throws StorageException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(resume);
        } catch (IOException e) {
            throw new StorageException("Couldn't write a file " + file.getAbsolutePath());
        }
    }

    protected Resume readFile(File file) throws StorageException {
        try (InputStream is = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (IOException e) {
            throw new StorageException("Couldn't read a file " + file.getAbsolutePath(), e);
        } catch (ClassNotFoundException e) {
            throw new StorageException("Exception of reading a resume.", e);
        }
    }

}
