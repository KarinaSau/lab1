
import java.util.ArrayList;
import java.util.List;

// IArchiveAdapter.java
interface IArchiveAdapter {
    void createArchive(String name);
    void addFile(String path);
    void extractArchive(String destination);
}

// ZipLibrary.java
class ZipLibrary {
    public void makeZip(String name) {
        System.out.println("[ZIP] Створення архіву: " + name + ".zip");
    }

    public void insertFile(String path) {
        System.out.println("[ZIP] Додано файл: " + path);
    }

    public void unpackZip(String destination) {
        System.out.println("[ZIP] Розпакування у: " + destination);
    }
}

// RarLibrary.java
class RarLibrary {
    public void createRar(String name) {
        System.out.println("[RAR] Створення архіву: " + name + ".rar");
    }

    public void appendFile(String path) {
        System.out.println("[RAR] Додано файл: " + path);
    }

    public void extractRar(String destination) {
        System.out.println("[RAR] Розпакування у: " + destination);
    }
}

// TarGzLibrary.java
class TarGzLibrary {
    public void buildTarGz(String name) {
        System.out.println("[TAR.GZ] Створення архіву: "
                + name + ".tar.gz");
    }

    public void putFile(String path) {
        System.out.println("[TAR.GZ] Додано файл: " + path);
    }

    public void unzipTarGz(String destination) {
        System.out.println("[TAR.GZ] Розпакування у: " + destination);
    }
}

// AceLibrary.java
class AceLibrary {
    public void generateAce(String name) {
        System.out.println("[ACE] Створення архіву: " + name + ".ace");
    }

    public void includeFile(String path) {
        System.out.println("[ACE] Додано файл: " + path);
    }

    public void openAce(String destination) {
        System.out.println("[ACE] Розпакування у: " + destination);
    }
}

// ZipAdapter.java
class ZipAdapter implements IArchiveAdapter {
    private ZipLibrary zipLib;

    public ZipAdapter(ZipLibrary lib) {
        this.zipLib = lib;
    }

    @Override
    public void createArchive(String name) {
        zipLib.makeZip(name);
    }

    @Override
    public void addFile(String path) {
        zipLib.insertFile(path);
    }

    @Override
    public void extractArchive(String destination) {
        zipLib.unpackZip(destination);
    }
}

// RarAdapter.java
class RarAdapter implements IArchiveAdapter {
    private RarLibrary rarLib;

    public RarAdapter(RarLibrary lib) {
        this.rarLib = lib;
    }

    @Override
    public void createArchive(String name) {
        rarLib.createRar(name);
    }

    @Override
    public void addFile(String path) {
        rarLib.appendFile(path);
    }

    @Override
    public void extractArchive(String destination) {
        rarLib.extractRar(destination);
    }
}

// TarGzAdapter.java
class TarGzAdapter implements IArchiveAdapter {
    private TarGzLibrary tarGzLib;

    public TarGzAdapter(TarGzLibrary lib) {
        this.tarGzLib = lib;
    }

    @Override
    public void createArchive(String name) {
        tarGzLib.buildTarGz(name);
    }

    @Override
    public void addFile(String path) {
        tarGzLib.putFile(path);
    }

    @Override
    public void extractArchive(String destination) {
        tarGzLib.unzipTarGz(destination);
    }
}

// AceAdapter.java
class AceAdapter implements IArchiveAdapter {
    private AceLibrary aceLib;

    public AceAdapter(AceLibrary lib) {
        this.aceLib = lib;
    }

    @Override
    public void createArchive(String name) {
        aceLib.generateAce(name);
    }

    @Override
    public void addFile(String path) {
        aceLib.includeFile(path);
    }

    @Override
    public void extractArchive(String destination) {
        aceLib.openAce(destination);
    }
}

// ArchiveManager.java
class ArchiveManager {
    private IArchiveAdapter zipAdapter;
    private IArchiveAdapter rarAdapter;
    private IArchiveAdapter tarGzAdapter;
    private IArchiveAdapter aceAdapter;

    public void setZipAdapter(IArchiveAdapter adapter) {
        this.zipAdapter = adapter;
    }

    public void setRarAdapter(IArchiveAdapter adapter) {
        this.rarAdapter = adapter;
    }

    public void setTarGzAdapter(IArchiveAdapter adapter) {
        this.tarGzAdapter = adapter;
    }

    public void setAceAdapter(IArchiveAdapter adapter) {
        this.aceAdapter = adapter;
    }

    public void createZipArchive(String name) {
        System.out.println("\n=== ZIP ARCHIVE ===");
        zipAdapter.createArchive(name);
        zipAdapter.addFile("document.txt");
        zipAdapter.addFile("photo.jpg");
        zipAdapter.extractArchive("output/zip");
    }

    public void createRarArchive(String name) {
        System.out.println("\n=== RAR ARCHIVE ===");
        rarAdapter.createArchive(name);
        rarAdapter.addFile("report.pdf");
        rarAdapter.extractArchive("output/rar");
    }

    public void createTarGzArchive(String name) {
        System.out.println("\n=== TAR.GZ ARCHIVE ===");
        tarGzAdapter.createArchive(name);
        tarGzAdapter.addFile("project.java");
        tarGzAdapter.extractArchive("output/targz");
    }

    public void createAceArchive(String name) {
        System.out.println("\n=== ACE ARCHIVE ===");
        aceAdapter.createArchive(name);
        aceAdapter.addFile("data.txt");
        aceAdapter.extractArchive("output/ace");
    }
}

// ClientApp / Main.java
public class Main {
    public static void main(String[] args) {
        ZipLibrary zipLibrary = new ZipLibrary();
        RarLibrary rarLibrary = new RarLibrary();
        TarGzLibrary tarGzLibrary = new TarGzLibrary();
        AceLibrary aceLibrary = new AceLibrary();

        IArchiveAdapter zipAdapter =
                new ZipAdapter(zipLibrary);

        IArchiveAdapter rarAdapter =
                new RarAdapter(rarLibrary);

        IArchiveAdapter tarGzAdapter =
                new TarGzAdapter(tarGzLibrary);

        IArchiveAdapter aceAdapter =
                new AceAdapter(aceLibrary);

        ArchiveManager manager = new ArchiveManager();

        manager.setZipAdapter(zipAdapter);
        manager.setRarAdapter(rarAdapter);
        manager.setTarGzAdapter(tarGzAdapter);
        manager.setAceAdapter(aceAdapter);

        manager.createZipArchive("backup");
        manager.createRarArchive("documents");
        manager.createTarGzArchive("project");
        manager.createAceArchive("data");
    }
}

