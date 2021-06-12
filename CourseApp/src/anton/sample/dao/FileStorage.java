package anton.sample.dao;

import anton.sample.exception.StorageException;
import anton.sample.model.Resume;

import java.io.File;
import java.util.List;

/**
 * User: Sedkov Anton
 * Date: 12.06.2021
 */
public class FileStorage extends AbstractStorage<File> {
    private File dir;

    public FileStorage(String path) {
        this.dir = new File(path);
        if (!dir.isDirectory() || !dir.canWrite()) {
            throw new IllegalArgumentException("'" + path + "' is not a directory or is not writable");
        }
    }

    @Override
    protected File getContext(String fileName) {
        return new File(fileName);
    }

    @Override
    protected void doClear() throws StorageException {
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(File file, Resume resume) {

    }

    @Override
    protected void doUpdate(File file, Resume resume) {

    }

    @Override
    protected Resume doLoad(File file) {
        return null;
    }

    @Override
    protected void doDelete(File file) throws StorageException {
        if (!file.delete())
            throw new StorageException("File " + file.getAbsolutePath() + " can not be deleted.");
    }


    @Override
    protected List<Resume> doGetAll() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
