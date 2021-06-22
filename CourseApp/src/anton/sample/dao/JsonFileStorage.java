package anton.sample.dao;

import anton.sample.exception.StorageException;
import anton.sample.model.Resume;
import anton.sample.util.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * User: Sedkov Anton
 * Date: 22.06.2021
 */
public class JsonFileStorage extends FileStorage {

    public JsonFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(OutputStream outputStream, Resume resume) throws IOException {
        try (Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            JsonParser.write(resume, writer);
        }
    }

    @Override
    protected Resume read(InputStream inputStream) throws IOException, StorageException {
        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return JsonParser.read(reader, Resume.class);
        }
    }

}
