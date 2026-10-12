
import java.util.ArrayList;
import java.util.List;

// ArchiveCreator.java
class ArchiveCreator {

    public void create(String name, String format) {
        System.out.println("Створення архіву: "
                + name + "." + format);
    }
}

// FileManager.java
class FileManager {

    private final List<String> files = new ArrayList<>();

    public void addFile(String path) {
        files.add(path);
        System.out.println("Додано файл: " + path);
    }

    public void removeFile(String path) {
        if (files.remove(path)) {
            System.out.println("Видалено файл: " + path);
        } else {
            System.out.println("Файл не знайдено: " + path);
        }
    }
}

// ArchiveValidator.java
class ArchiveValidator {

    public boolean testArchive() {
        System.out.println(
                "Перевірка структури архіву (демонстрація)..."
        );
        return true;
    }
}

// ChecksumService.java
class ChecksumService {

    public String calculateChecksum() {
        String checksum = "DEMO-12345";

        System.out.println(
                "Контрольна сума (демо): " + checksum
        );

        return checksum;
    }

    public boolean verifyChecksum() {
        System.out.println(
                "Перевірка контрольної суми (демонстрація)..."
        );
        return true;
    }
}

// ArchiveFacade.java
class ArchiveFacade {

    private final ArchiveCreator creator;
    private final FileManager fileManager;
    private final ArchiveValidator validator;
    private final ChecksumService checksumService;

    public ArchiveFacade() {
        creator = new ArchiveCreator();
        fileManager = new FileManager();
        validator = new ArchiveValidator();
        checksumService = new ChecksumService();
    }

    public void createArchive(String name, String format) {
        creator.create(name, format);
    }

    public void addFile(String path) {
        fileManager.addFile(path);
    }

    public void removeFile(String path) {
        fileManager.removeFile(path);
    }

    public boolean verifyArchive() {
        boolean validStructure = validator.testArchive();

        checksumService.calculateChecksum();

        boolean validChecksum =
                checksumService.verifyChecksum();

        boolean result = validStructure && validChecksum;

        System.out.println(
                "Результат перевірки (демо): "
                        + (result ? "успішно" : "помилка")
        );

        return result;
    }
}

// UserInterface.java
class UserInterface {

    private final ArchiveFacade facade;

    public UserInterface(ArchiveFacade facade) {
        this.facade = facade;
    }

    public void createArchive(String name, String format) {
        facade.createArchive(name, format);
    }

    public void addFile(String path) {
        facade.addFile(path);
    }

    public void removeFile(String path) {
        facade.removeFile(path);
    }

    public void verifyArchive() {
        facade.verifyArchive();
    }
}

// Main.java
public class Main {

    public static void main(String[] args) {

        ArchiveFacade facade = new ArchiveFacade();
        UserInterface ui = new UserInterface(facade);

        // Створення ZIP-архіву
        ui.createArchive("backup", "zip");

        // Додавання файлів
        ui.addFile("document.txt");
        ui.addFile("photo.jpg");

        // Видалення файлу
        ui.removeFile("photo.jpg");

        // Перевірка архіву
        ui.verifyArchive();

        // Демонстрація іншого формату
        System.out.println("\nІнший формат:");
        ui.createArchive("project", "tar.gz");
    }
}
