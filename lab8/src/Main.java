
import java.util.ArrayList;
import java.util.List;

// IArchiveVisitor.java
interface IArchiveVisitor {
    void visit(ArchiveFile file);
    void visit(ArchiveFolder folder);
}

// IArchiveElement.java
interface IArchiveElement {
    void accept(IArchiveVisitor visitor);
}

// ArchiveFile.java
class ArchiveFile implements IArchiveElement {

    public final String fileName;
    public final int size;

    public ArchiveFile(String fileName, int size) {
        this.fileName = fileName;
        this.size = size;
    }

    @Override
    public void accept(IArchiveVisitor visitor) {
        visitor.visit(this);
    }
}

// ArchiveFolder.java
class ArchiveFolder implements IArchiveElement {

    public final String folderName;

    public ArchiveFolder(String folderName) {
        this.folderName = folderName;
    }

    @Override
    public void accept(IArchiveVisitor visitor) {
        visitor.visit(this);
    }
}

// ArchiveStatisticsVisitor.java
class ArchiveStatisticsVisitor implements IArchiveVisitor {

    private int totalSize = 0;
    private int fileCount = 0;
    private int folderCount = 0;

    @Override
    public void visit(ArchiveFile file) {
        totalSize += file.size;
        fileCount++;
    }

    @Override
    public void visit(ArchiveFolder folder) {
        folderCount++;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public int getFileCount() {
        return fileCount;
    }

    public int getFolderCount() {
        return folderCount;
    }
}

// ArchiveReportVisitor.java
class ArchiveReportVisitor implements IArchiveVisitor {

    private final StringBuilder reportBuilder =
            new StringBuilder();

    @Override
    public void visit(ArchiveFile file) {
        reportBuilder.append("Файл: ")
                .append(file.fileName)
                .append(" (розмір: ")
                .append(file.size)
                .append(" байт)\n");
    }

    @Override
    public void visit(ArchiveFolder folder) {
        reportBuilder.append("Папка: ")
                .append(folder.folderName)
                .append("\n");
    }

    public String getReport() {
        return reportBuilder.toString();
    }
}

// ArchiveService.java
class ArchiveService {

    public void generateArchiveReport(
            List<IArchiveElement> data) {

        ArchiveReportVisitor visitor =
                new ArchiveReportVisitor();

        for (IArchiveElement element : data) {
            element.accept(visitor);
        }

        System.out.println(
                "=== ЗВІТ ПРО ВМІСТ АРХІВУ ==="
        );
        System.out.print(visitor.getReport());
    }

    public void exportArchiveStatistics(
            List<IArchiveElement> data) {

        ArchiveStatisticsVisitor visitor =
                new ArchiveStatisticsVisitor();

        for (IArchiveElement element : data) {
            element.accept(visitor);
        }

        System.out.println(
                "=== СТАТИСТИКА АРХІВУ ==="
        );

        System.out.println(
                "Файлів: " + visitor.getFileCount()
        );

        System.out.println(
                "Папок: " + visitor.getFolderCount()
        );

        System.out.println(
                "Загальний розмір: "
                        + visitor.getTotalSize() + " байт"
        );
    }
}

// Main.java
public class Main {

    public static void main(String[] args) {

        // Створення колекції елементів архіву
        List<IArchiveElement> elements =
                new ArrayList<>();

        // Додавання папок і файлів
        elements.add(new ArchiveFolder("documents"));
        elements.add(new ArchiveFile("report.pdf", 1200));
        elements.add(new ArchiveFile("notes.txt", 300));

        elements.add(new ArchiveFolder("images"));
        elements.add(new ArchiveFile("photo.jpg", 2500));

        // Створення сервісу архіватора
        ArchiveService service = new ArchiveService();

        // Формування звіту
        service.generateArchiveReport(elements);

        System.out.println();

        // Обчислення статистики
        service.exportArchiveStatistics(elements);
    }
}
