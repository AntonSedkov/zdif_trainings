package anton.sample.dao;

import anton.sample.exception.StorageException;
import anton.sample.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * User: Sedkov Anton
 * Date: 12.06.2021
 */
public class DataStreamFileStorage extends FileStorage {
    private static final String DEFAULT_STRING = "EMPTY_STRING";

    public DataStreamFileStorage(String path) {
        super(path);
    }

    protected void writeFile(File file, Resume resume) throws StorageException {
        try (FileOutputStream fos = new FileOutputStream(file);
             DataOutputStream dos = new DataOutputStream(fos)) {
            writeString(dos, resume.getFullName());
            writeString(dos, resume.getLocation());
            writeString(dos, resume.getHomePage());
            Map<ContactType, String> contacts = resume.getContacts();
            writeCollection(dos, contacts.entrySet(), entry -> {
                writeString(dos, entry.getKey().name());
                writeString(dos, entry.getValue());
            });
            Map<SectionType, Section> sections = resume.getSections();
            writeCollection(dos, sections.entrySet(), entry -> {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                writeString(dos, type.name());
                switch (type) {
                    case OBJECTIVE:
                        writeString(dos, ((TextWithTitleSection) section).getTitle());
                        writeString(dos, ((TextWithTitleSection) section).getComment());
                        break;
                    case ACHIEVEMENT, QUALIFICATION:
                        writeCollection(dos, ((MultiTextSection) section).getValues(), string -> writeString(dos, string));
                        break;
                    case EXPERIENCE, EDUCATION:
                        //todo
                        break;
                    default:
                        throw new EnumConstantNotPresentException(SectionType.class, type.name());
                }
            });
        } catch (IOException e) {
            throw new StorageException("Couldn't write a file " + file.getAbsolutePath());
        }
    }

    protected Resume readFile(File file) throws StorageException {
        Resume resume = new Resume();
        resume.setUuid(file.getName());
        try (InputStream is = new FileInputStream(file);
             DataInputStream dis = new DataInputStream(is)) {
            resume.setFullName(readString(dis));
            resume.setLocation(readString(dis));
            resume.setHomePage(readString(dis));
            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                resume.addContact(ContactType.valueOf(readString(dis)), readString(dis));
            }
            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                //         resume.addSection(SectionType.valueOf(dis.readUTF()), dis.readUTF());//todo
            }
        } catch (IOException e) {
            throw new StorageException("Couldn't read a file " + file.getAbsolutePath());
        }
        return resume;
    }

    private void writeString(DataOutputStream dos, String str) throws IOException {
        dos.writeUTF(str == null ? DEFAULT_STRING : str);
    }

    private String readString(DataInputStream dis) throws IOException {
        String str = dis.readUTF();
        return str.equals(DEFAULT_STRING) ? null : str;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int listSize = dis.readInt();
        List<T> list = new ArrayList<>(listSize);
        for (int i = 0; i < listSize; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }
}