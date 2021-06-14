package anton.sample.dao;

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

    @Override
    protected void write(OutputStream outputStream, Resume resume) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(outputStream)) {
            writeString(dos, resume.getUuid());
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
                    case OBJECTIVE -> {
                        writeString(dos, ((TextWithTitleSection) section).getTitle());
                        writeString(dos, ((TextWithTitleSection) section).getComment());
                    }
                    case ACHIEVEMENT, QUALIFICATION -> writeCollection(dos, ((MultiTextSection) section).getValues(), string -> writeString(dos, string));
                    case EXPERIENCE, EDUCATION -> System.out.println("");                        //todo
                    default -> throw new EnumConstantNotPresentException(SectionType.class, type.name());
                }
            });
        }
    }

    @Override
    protected Resume read(InputStream inputStream) throws IOException {
        Resume resume = new Resume();
        try (DataInputStream dis = new DataInputStream(inputStream)) {
            resume.setUuid(readString(dis));
            resume.setFullName(readString(dis));
            resume.setLocation(readString(dis));
            resume.setHomePage(readString(dis));
            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                resume.addContact(ContactType.valueOf(readString(dis)), readString(dis));
            }
            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType sectionType = SectionType.valueOf(readString(dis));
                switch (sectionType) {
                    case OBJECTIVE -> resume.addObjective(readString(dis), readString(dis));
                    case ACHIEVEMENT, QUALIFICATION -> resume.addSection(sectionType,
                            new MultiTextSection(readList(dis, () -> readString(dis))));
                    case EDUCATION, EXPERIENCE -> System.out.println("");//todo
                    default -> throw new EnumConstantNotPresentException(SectionType.class, sectionType.name());
                }
            }
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