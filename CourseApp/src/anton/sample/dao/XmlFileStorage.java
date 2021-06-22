package anton.sample.dao;

import anton.sample.exception.StorageException;
import anton.sample.model.*;
import anton.sample.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * User: Sedkov Anton
 * Date: 14.06.2021
 */
public class XmlFileStorage extends FileStorage {
    private XmlParser xmlParser;

    public XmlFileStorage(String path) {
        super(path);
        xmlParser = new XmlParser(Resume.class, Organization.class, Link.class,
                OrganizationSection.class, TextWithTitleSection.class, MultiTextSection.class, OrganizationPeriod.class);
    }

    @Override
    protected void write(OutputStream outputStream, Resume resume) throws IOException, StorageException {
        try (Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            xmlParser.marshall(resume, writer);
        }
    }

    @Override
    protected Resume read(InputStream inputStream) throws IOException, StorageException {
        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(reader);
        }
    }

    @Override
    public boolean isSectionSupported() {
        return false;
    }
}
