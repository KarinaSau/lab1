
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// ArchiveEntry.java
class ArchiveEntry {
    private String name;
    private String path;
    private long size;
    private String type;
    private String checksum;
}

// Metadata.java
class Metadata {
    private String comment;
    private Date createdAt;
    private Date modifiedAt;

    public void editMetadata() {
        // ...
    }
}

// Archive.java
class Archive {
    private String name;
    private String path;
    private String format;
    private long size;
    private List<ArchiveEntry> entries = new ArrayList<>();
    private Metadata metadata;

    public void addEntry(ArchiveEntry entry) {
        // ...
    }

    public void removeEntry(ArchiveEntry entry) {
        // ...
    }

    public List<ArchiveEntry> getEntries() {
        return entries;
    }
}

// ArchiveRepository.java
class ArchiveRepository {
    private List<Archive> archives = new ArrayList<>();

    public void saveArchive(Archive archive) {
        // ...
    }

    public Archive getArchive(String path) {
        // ...
        return null;
    }

    public void updateArchive(Archive archive) {
        // ...
    }

    public void deleteArchive(String path) {
        // ...
    }
}

// IArchiveAdapter.java
interface IArchiveAdapter {
    void create(Archive archive);
    Archive open(String path);
    void add(ArchiveEntry entry);
    void remove(ArchiveEntry entry);
    void save(Archive archive);
    boolean test(Archive archive);
}

// ZipAdapter.java
class ZipAdapter implements IArchiveAdapter {
    public void create(Archive archive) {
        // ...
    }

    public Archive open(String path) {
        // ...
        return null;
    }

    public void add(ArchiveEntry entry) {
        // ...
    }

    public void remove(ArchiveEntry entry) {
        // ...
    }

    public void save(Archive archive) {
        // ...
    }

    public boolean test(Archive archive) {
        // ...
        return false;
    }
}

// RarAdapter.java
class RarAdapter implements IArchiveAdapter {
    public void create(Archive archive) {
        // ...
    }

    public Archive open(String path) {
        // ...
        return null;
    }

    public void add(ArchiveEntry entry) {
        // ...
    }

    public void remove(ArchiveEntry entry) {
        // ...
    }

    public void save(Archive archive) {
        // ...
    }

    public boolean test(Archive archive) {
        // ...
        return false;
    }
}

// TarGzAdapter.java
class TarGzAdapter implements IArchiveAdapter {
    public void create(Archive archive) {
        // ...
    }

    public Archive open(String path) {
        // ...
        return null;
    }

    public void add(ArchiveEntry entry) {
        // ...
    }

    public void remove(ArchiveEntry entry) {
        // ...
    }

    public void save(Archive archive) {
        // ...
    }

    public boolean test(Archive archive) {
        // ...
        return false;
    }
}

// AceAdapter.java
class AceAdapter implements IArchiveAdapter {
    public void create(Archive archive) {
        // ...
    }

    public Archive open(String path) {
        // ...
        return null;
    }

    public void add(ArchiveEntry entry) {
        // ...
    }

    public void remove(ArchiveEntry entry) {
        // ...
    }

    public void save(Archive archive) {
        // ...
    }

    public boolean test(Archive archive) {
        // ...
        return false;
    }
}

// ChecksumService.java
class ChecksumService {
    public String calculateChecksum(ArchiveEntry file) {
        // ...
        return "";
    }

    public boolean verifyChecksum(ArchiveEntry file) {
        // ...
        return false;
    }
}

// ArchiveTester.java
class ArchiveTester {
    public boolean testArchive(Archive archive) {
        // ...
        return false;
    }

    public List<ArchiveEntry> findCorruptedEntries() {
        // ...
        return new ArrayList<>();
    }
}

// ArchiveSplitter.java
class ArchiveSplitter {
    public void splitArchive(Archive archive, long partSize) {
        // ...
    }
}

// ArchiveManager.java
class ArchiveManager {
    private ArchiveRepository repository = new ArchiveRepository();
    private IArchiveAdapter adapter;

    public void createArchive() {
        // ...
    }

    public void openArchive() {
        // ...
    }

    public void saveArchive() {
        // ...
    }

    public void addEntry() {
        // ...
    }

    public void removeEntry() {
        // ...
    }

    public void editMetadata() {
        // ...
    }
}

// UserInterface.java
class UserInterface {
    private ArchiveManager archiveManager = new ArchiveManager();

    public void createArchive() {
        // ...
    }

    public void openArchive() {
        // ...
    }

    public void editArchive() {
        // ...
    }

    public void testArchive() {
        // ...
    }

    public void splitArchive() {
        // ...
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        System.out.println("Лабораторна робота №2");
        System.out.println("Варіант 14 — Архіватор");
        System.out.println("Структуру класів створено.");

        UserInterface ui = new UserInterface();
        ui.createArchive();
    }
}
