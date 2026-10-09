import java.util.ArrayList;
import java.util.List;

// ArchiveItem.java
class ArchiveItem {
    private String name;
    private String path;
    private long size;

    public ArchiveItem(String name, String path, long size) {
        this.name = name;
        this.path = path;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public long getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "ArchiveItem{name='" + name + "', path='" + path + "', size=" + size + "}";
    }
}

// ICompressionStrategy.java
interface ICompressionStrategy {
    void compress(List<ArchiveItem> items, String archiveName);
}

// ZipCompressionStrategy.java
class ZipCompressionStrategy implements ICompressionStrategy {
    @Override
    public void compress(List<ArchiveItem> items, String archiveName) {
        System.out.println("Створення ZIP-архіву: " + archiveName + ".zip");
        for (ArchiveItem item : items) {
            System.out.println("Додано до ZIP: " + item.getName());
        }
        System.out.println("ZIP-архів успішно створено.\n");
    }
}

// RarCompressionStrategy.java
class RarCompressionStrategy implements ICompressionStrategy {
    @Override
    public void compress(List<ArchiveItem> items, String archiveName) {
        System.out.println("Створення RAR-архіву: " + archiveName + ".rar");
        for (ArchiveItem item : items) {
            System.out.println("Додано до RAR: " + item.getName());
        }
        System.out.println("RAR-архів успішно створено.\n");
    }
}

// TarGzCompressionStrategy.java
class TarGzCompressionStrategy implements ICompressionStrategy {
    @Override
    public void compress(List<ArchiveItem> items, String archiveName) {
        System.out.println("Створення TAR.GZ-архіву: " + archiveName + ".tar.gz");
        for (ArchiveItem item : items) {
            System.out.println("Додано до TAR.GZ: " + item.getName());
        }
        System.out.println("TAR.GZ-архів успішно створено.\n");
    }
}

// ArchiveManager.java
class ArchiveManager {
    private List<ArchiveItem> items = new ArrayList<>();
    private ICompressionStrategy strategy;
    private String archiveName;

    public ArchiveManager(String archiveName) {
        this.archiveName = archiveName;
    }

    public void setStrategy(ICompressionStrategy strategy) {
        this.strategy = strategy;
    }

    public void addItem(ArchiveItem item) {
        items.add(item);
        System.out.println("Додано файл/папку: " + item.getName());
    }

    public void removeItem(ArchiveItem item) {
        items.remove(item);
        System.out.println("Видалено файл/папку: " + item.getName());
    }

    public void createArchive() {
        if (strategy == null) {
            System.out.println("Помилка: не вибрано стратегію архівування.\n");
            return;
        }

        if (items.isEmpty()) {
            System.out.println("Помилка: список елементів для архівації порожній.\n");
            return;
        }

        strategy.compress(items, archiveName);
    }

    public boolean testArchive() {
        System.out.println("Перевірка архіву " + archiveName + " на наявність пошкоджень...");
        System.out.println("Архів не містить пошкоджень.\n");
        return true;
    }

    public boolean verifyChecksum() {
        System.out.println("Перевірка checksum архіву " + archiveName + "...");
        System.out.println("Checksum успішно підтверджено.\n");
        return true;
    }
}

// ArchiveApplication.java
class ArchiveApplication {
    public void createArchive(ArchiveManager manager) {
        manager.createArchive();
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        ArchiveManager manager = new ArchiveManager("backup");

        ArchiveItem file1 = new ArchiveItem("document.txt", "/files/document.txt", 1200);
        ArchiveItem file2 = new ArchiveItem("photo.jpg", "/images/photo.jpg", 5400);
        ArchiveItem folder1 = new ArchiveItem("projects", "/work/projects", 15000);

        manager.addItem(file1);
        manager.addItem(file2);
        manager.addItem(folder1);

        ArchiveApplication app = new ArchiveApplication();

        // Використання ZIP-стратегії
        manager.setStrategy(new ZipCompressionStrategy());
        app.createArchive(manager);
        manager.testArchive();
        manager.verifyChecksum();

        // Зміна стратегії на RAR
        manager.setStrategy(new RarCompressionStrategy());
        app.createArchive(manager);

        // Зміна стратегії на TAR.GZ
        manager.setStrategy(new TarGzCompressionStrategy());
        app.createArchive(manager);
    }
}
