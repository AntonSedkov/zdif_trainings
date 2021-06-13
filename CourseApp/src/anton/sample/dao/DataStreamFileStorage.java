package anton.sample.dao;

import anton.sample.exception.StorageException;
import anton.sample.model.ContactType;
import anton.sample.model.Resume;
import anton.sample.model.Section;
import anton.sample.model.SectionType;

import java.io.*;
import java.util.Map;

/**
 * User: Sedkov Anton
 * Date: 12.06.2021
 */
public class DataStreamFileStorage extends FileStorage {

    public DataStreamFileStorage(String path) {
        super(path);
    }

    protected void writeFile(File file, Resume resume) throws StorageException {
        try (FileOutputStream fos = new FileOutputStream(file);
             DataOutputStream dos = new DataOutputStream(fos)) {
            dos.writeUTF(resume.getFullName());
            dos.writeUTF(resume.getLocation());
            dos.writeUTF(resume.getHomePage());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue().toString()); //todo
            }
        } catch (IOException e) {
            throw new StorageException("Couldn't write a file " + file.getAbsolutePath());
        }
    }

    protected Resume readFile(File file) throws StorageException {
        Resume resume = new Resume();
        try (InputStream is = new FileInputStream(file);
             DataInputStream dis = new DataInputStream(is)) {
            resume.setFullName(dis.readUTF());
            resume.setLocation(dis.readUTF());
            resume.setHomePage(dis.readUTF());
            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
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

}
