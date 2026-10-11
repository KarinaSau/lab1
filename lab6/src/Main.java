// IArchiver.java
interface IArchiver {
    void createArchive(String name);
    void addFile(String path);
    void extractArchive(String destination);
}

// ZipArchiver.java
class ZipArchiver implements IArchiver {

    @Override
    public void createArchive(String name) {
        System.out.println("[ZIP] Створення архіву: " + name + ".zip");
    }

    @Override
    public void addFile(String path) {
        System.out.println("[ZIP] Додавання файлу: " + path);
    }

    @Override
    public void extractArchive(String destination) {
        System.out.println("[ZIP] Розпакування у: " + destination);
    }
}

// RarArchiver.java
class RarArchiver implements IArchiver {

    @Override
    public void createArchive(String name) {
        System.out.println("[RAR] Створення архіву: " + name + ".rar");
    }

    @Override
    public void addFile(String path) {
        System.out.println("[RAR] Додавання файлу: " + path);
    }

    @Override
    public void extractArchive(String destination) {
        System.out.println("[RAR] Розпакування у: " + destination);
    }
}

// TarGzArchiver.java
class TarGzArchiver implements IArchiver {

    @Override
    public void createArchive(String name) {
        System.out.println("[TAR.GZ] Створення архіву: "
                + name + ".tar.gz");
    }

    @Override
    public void addFile(String path) {
        System.out.println("[TAR.GZ] Додавання файлу: " + path);
    }

    @Override
    public void extractArchive(String destination) {
        System.out.println("[TAR.GZ] Розпакування у: " + destination);
    }
}

// AceArchiver.java
class AceArchiver implements IArchiver {

    @Override
    public void createArchive(String name) {
        System.out.println("[ACE] Створення архіву: " + name + ".ace");
    }

    @Override
    public void addFile(String path) {
        System.out.println("[ACE] Додавання файлу: " + path);
    }

    @Override
    public void extractArchive(String destination) {
        System.out.println("[ACE] Розпакування у: " + destination);
    }
}

// ArchiveCreator.java
abstract class ArchiveCreator {

    // Фабричний метод
    public abstract IArchiver createArchiver();

    // Спільна логіка використання створеного продукту
    public void createAndArchive(String name) {
        IArchiver archiver = createArchiver();
        archiver.createArchive(name);
    }
}

// ZipCreator.java
class ZipCreator extends ArchiveCreator {

    @Override
    public IArchiver createArchiver() {
        return new ZipArchiver();
    }
}

// RarCreator.java
class RarCreator extends ArchiveCreator {

    @Override
    public IArchiver createArchiver() {
        return new RarArchiver();
    }
}

// TarGzCreator.java
class TarGzCreator extends ArchiveCreator {

    @Override
    public IArchiver createArchiver() {
        return new TarGzArchiver();
    }
}

// AceCreator.java
class AceCreator extends ArchiveCreator {

    @Override
    public IArchiver createArchiver() {
        return new AceArchiver();
    }
}

// ArchiveManager.java
class ArchiveManager {

    private ArchiveCreator creator;
    private IArchiver archiver;

    public ArchiveManager(ArchiveCreator creator) {
        setCreator(creator);
    }

    public void setCreator(ArchiveCreator creator) {
        if (creator == null) {
            throw new IllegalArgumentException(
                    "Фабрика архівування не може бути null"
            );
        }

        this.creator = creator;
        this.archiver = creator.createArchiver();
    }

    public void processArchive(String name) {
        System.out.println("\n=== РОБОТА З АРХІВОМ ===");

        archiver.createArchive(name);
        archiver.addFile("document.txt");
        archiver.addFile("photo.jpg");
        archiver.extractArchive("output");

        System.out.println("Операції завершено.");
    }
}

// Main.java
public class Main {

    public static void main(String[] args) {

        // Створення фабрики ZIP
        ArchiveCreator zipFactory = new ZipCreator();

        ArchiveManager manager =
                new ArchiveManager(zipFactory);

        manager.processArchive("backup");

        // Заміна фабрики на RAR
        ArchiveCreator rarFactory = new RarCreator();
        manager.setCreator(rarFactory);
        manager.processArchive("documents");

        // Заміна фабрики на TAR.GZ
        ArchiveCreator tarGzFactory = new TarGzCreator();
        manager.setCreator(tarGzFactory);
        manager.processArchive("project");

        // Заміна фабрики на ACE
        ArchiveCreator aceFactory = new AceCreator();
        manager.setCreator(aceFactory);
        manager.processArchive("data");
    }
}
