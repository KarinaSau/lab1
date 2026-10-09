
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Archive.java
class Archive {
    private String name;
    private String format;
    private String path;
    private String checksum;

    public Archive(String name, String format, String path) {
        this.name = name;
        this.format = format;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getFormat() {
        return format;
    }

    public String getPath() {
        return path;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
}

// FileData.java
class FileData {
    private String fileName;
    private String filePath;
    private long size;

    public FileData(String fileName, String filePath, long size) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public long getSize() {
        return size;
    }
}

// FileRepository.java
class FileRepository {

    public List<FileData> readSourceFiles(List<String> filePaths) {
        List<FileData> files = new ArrayList<>();

        for (String path : filePaths) {
            System.out.println("[FileRepository] Читання файлу: " + path);

            files.add(new FileData(
                    path.substring(path.lastIndexOf("/") + 1),
                    path,
                    1024
            ));
        }

        return files;
    }

    public String saveArchive(String archiveName, String format) {
        String path = "archives/" + archiveName + "." + format;

        System.out.println(
                "[FileRepository] Архів збережено: " + path
        );

        return path;
    }

    public void saveChecksum(String archivePath, String checksum) {
        System.out.println(
                "[FileRepository] Checksum збережено для "
                        + archivePath + ": " + checksum
        );
    }
}

// ArchiveService.java
class ArchiveService {

    private FileRepository repository;

    public ArchiveService(FileRepository repository) {
        this.repository = repository;
    }

    public Archive createArchive(
            List<String> filePaths,
            String archiveName,
            String format
    ) {

        System.out.println(
                "[ArchiveService] Створення архіву формату: " + format
        );

        validateParameters(format);

        List<FileData> files =
                repository.readSourceFiles(filePaths);

        System.out.println(
                "[ArchiveService] Отримано файлів: " + files.size()
        );

        String archivePath =
                repository.saveArchive(archiveName, format);

        Archive archive =
                new Archive(archiveName, format, archivePath);

        String checksum =
                calculateChecksum(archivePath);

        archive.setChecksum(checksum);

        repository.saveChecksum(
                archivePath,
                checksum
        );

        return archive;
    }

    private void validateParameters(String format) {
        if (!format.equals("zip")
                && !format.equals("rar")
                && !format.equals("tar.gz")
                && !format.equals("ace")) {

            throw new IllegalArgumentException(
                    "Непідтримуваний формат архіву"
            );
        }
    }

    private String calculateChecksum(String archivePath) {
        System.out.println(
                "[ArchiveService] Обчислення checksum..."
        );

        return Integer.toHexString(
                archivePath.hashCode()
        );
    }
}

// TransferService.java
class TransferService {

    public void sendArchive(
            Archive archive,
            String peerAddress
    ) {

        System.out.println(
                "[TransferService] Передача архіву "
                        + archive.getName()
                        + " на P2P вузол "
                        + peerAddress
        );

        System.out.println(
                "[TransferService] Передачу завершено успішно."
        );
    }

    public void receiveArchive(String archiveName) {
        System.out.println(
                "[TransferService] Отримано архів: "
                        + archiveName
        );
    }
}

// ArchiverUI.java
class ArchiverUI {

    private ArchiveService archiveService;
    private TransferService transferService;

    public ArchiverUI(
            ArchiveService archiveService,
            TransferService transferService
    ) {
        this.archiveService = archiveService;
        this.transferService = transferService;
    }

    public Archive createArchive(
            List<String> files,
            String name,
            String format
    ) {

        System.out.println(
                "\n[UI] Користувач створює архів..."
        );

        Archive archive =
                archiveService.createArchive(
                        files,
                        name,
                        format
                );

        System.out.println(
                "[UI] Архів успішно створено."
        );

        System.out.println(
                "[UI] Шлях: " + archive.getPath()
        );

        System.out.println(
                "[UI] Checksum: " + archive.getChecksum()
        );

        return archive;
    }

    public void sendArchive(
            Archive archive,
            String peer
    ) {

        System.out.println(
                "\n[UI] Передача архіву..."
        );

        transferService.sendArchive(
                archive,
                peer
        );
    }
}

// Main.java
public class Main {

    public static void main(String[] args) {

        FileRepository repository =
                new FileRepository();

        ArchiveService archiveService =
                new ArchiveService(repository);

        TransferService transferService =
                new TransferService();

        ArchiverUI ui =
                new ArchiverUI(
                        archiveService,
                        transferService
                );

        List<String> files = Arrays.asList(
                "source_files/document.txt",
                "source_files/photo.jpg",
                "source_files/report.pdf"
        );

        Archive archive =
                ui.createArchive(
                        files,
                        "my_archive",
                        "zip"
                );

        ui.sendArchive(
                archive,
                "192.168.1.25"
        );
    }
}
