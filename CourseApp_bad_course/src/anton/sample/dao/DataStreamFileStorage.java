package anton.sample.dao;

import anton.sample.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

/**
 * User: Sedkov Anton
 * Date: 12.06.2021
 */
public class DataStreamFileStorage extends FileStorage {
    public DataStreamFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(OutputStream outputStream, Resume resume) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(outputStream)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            dos.writeUTF(resume.getLocation());
            dos.writeUTF(resume.getHomePage());
            Map<ContactType, String> contacts = resume.getContacts();
            writeCollection(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            Map<SectionType, Section> sections = resume.getSections();
            writeCollection(dos, sections.entrySet(), entry -> {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case OBJECTIVE -> {
                        dos.writeUTF(((TextWithTitleSection) section).getTitle());
                        dos.writeUTF(((TextWithTitleSection) section).getComment());
                    }
                    case ACHIEVEMENT, QUALIFICATION -> writeCollection(dos, ((MultiTextSection) section).getValues(), dos::writeUTF);
                    case EXPERIENCE, EDUCATION -> writeCollection(
                            dos, ((OrganizationSection) section).getOrganizations(), organization -> {
                                dos.writeUTF(organization.getLink().name());
                                dos.writeUTF(organization.getLink().url());
                                writeCollection(dos, organization.getPeriods(), organizationPeriod -> {
                                    writeLocalDate(dos, organizationPeriod.getStartDate());
                                    writeLocalDate(dos, organizationPeriod.getEndDate());
                                    dos.writeUTF(organizationPeriod.getPosition());
                                    dos.writeUTF(organizationPeriod.getContent());
                                });
                            });
                    default -> throw new EnumConstantNotPresentException(SectionType.class, type.name());
                }
            });
        }
    }

    @Override
    protected Resume read(InputStream inputStream) throws IOException {
        Resume resume = new Resume();
        try (DataInputStream dis = new DataInputStream(inputStream)) {
            resume.setUuid(dis.readUTF());
            resume.setFullName(dis.readUTF());
            resume.setLocation(dis.readUTF());
            resume.setHomePage(dis.readUTF());
            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case OBJECTIVE -> resume.addObjective(dis.readUTF(), dis.readUTF());
                    case ACHIEVEMENT, QUALIFICATION -> resume.addSection(sectionType,
                            new MultiTextSection(readList(dis, dis::readUTF)));
                    case EDUCATION, EXPERIENCE -> resume.addSection(sectionType,
                            new OrganizationSection(readList(dis, () -> new Organization(new Link(dis.readUTF(), dis.readUTF()),
                                    readList(dis, () -> new OrganizationPeriod(readLocalDate(dis),
                                            readLocalDate(dis), dis.readUTF(), dis.readUTF()))))));
                    default -> throw new EnumConstantNotPresentException(SectionType.class, sectionType.name());
                }
            }
        }
        return resume;
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

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        Objects.requireNonNull(ld, "LocalDate cannot be null, use OrganizationPeriod.NOWADAYS");
        dos.writeInt(ld.getYear());
        dos.writeUTF(ld.getMonth().name());
        dos.writeInt(ld.getDayOfMonth());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), Month.valueOf(dis.readUTF()), dis.readInt());
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }
}