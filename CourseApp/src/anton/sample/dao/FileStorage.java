package anton.sample.dao;

import anton.sample.exception.StorageException;
import anton.sample.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: Sedkov Anton
 * Date: 12.06.2021
 */
public abstract class FileStorage extends AbstractStorage<File> {
    private final File dir;

    public FileStorage(String path) {
        this.dir = new File(path);
        if (!dir.isDirectory() || !dir.canWrite()) {
            throw new IllegalArgumentException("'" + path + "' is not a directory or is not writable");
        }
    }

    @Override
    protected File getContext(String fileName) {
        return new File(dir, fileName);
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
    protected void doSave(File file, Resume resume) throws StorageException {
        try {
            file.createNewFile();
            writeFile(file, resume);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath());
        }
    }

    @Override
    protected void doUpdate(File file, Resume resume) throws StorageException {
        writeFile(file, resume);
    }

    @Override
    protected Resume doLoad(File file) throws StorageException {
        return readFile(file);
    }

    @Override
    protected void doDelete(File file) throws StorageException {
        if (!file.delete())
            throw new StorageException("File " + file.getAbsolutePath() + " can not be deleted.");
    }


    @Override
    protected List<Resume> doGetAll() throws StorageException {
        File[] files = dir.listFiles();
        if (files == null) {
            return Collections.emptyList();
        }
        List<Resume> resumes = new ArrayList<>(files.length);
        for (File file : files) {
            resumes.add(readFile(file));
        }
        return resumes;
    }

    @Override
    public int size() {
        int result = 0;
        String[] list = dir.list();
        if (list != null) {
            result = list.length;
        }
        return result;
    }

    protected void writeFile(File file, Resume resume) throws StorageException {
        try {
            write(new FileOutputStream(file), resume);
        } catch (IOException e) {
            throw new StorageException("Couldn't write a file " + file.getAbsolutePath(), e);
        }
    }

    protected Resume readFile(File file) throws StorageException {
        try {
            return read(new FileInputStream(file));
        } catch (IOException e) {
            throw new StorageException("Couldn't read a file " + file.getAbsolutePath(), e);
        }
    }

    abstract protected void write(OutputStream outputStream, Resume resume) throws IOException;

    abstract protected Resume read(InputStream inputStream) throws IOException, StorageException;

}
