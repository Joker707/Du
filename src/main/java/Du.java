import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.nio.file.DirectoryStream;

public class Du {

    private boolean usableFormat;
    private boolean totalSum;
    private boolean otherBase;

    public Du(boolean usableFormat, boolean totalSum, boolean otherBase) {
        this.usableFormat = usableFormat;
        this.totalSum = totalSum;
        this.otherBase = otherBase;
    }

    public int size(Path path) throws IOException {
        int size = 0;
        ArrayList<Path> pathslist = new ArrayList<>(Collections.singletonList(path));
        ArrayList<Path> directoryfiles = new ArrayList<>();
        while (!pathslist.isEmpty()) {
            Iterator<Path> iterator = pathslist.iterator();
            while (iterator.hasNext()) {
                Path nextpath = iterator.next();
                if (Files.isDirectory(nextpath)) {
                    DirectoryStream<Path> stream = Files.newDirectoryStream(nextpath);
                    for (Path entry : stream) {
                        directoryfiles.add(entry);
                    }
                } else {
                    size += Files.size(nextpath);
                }
                iterator.remove();
            }
            pathslist.addAll(directoryfiles);
            directoryfiles.clear();
        }
        return size;
    }


    public String result(String[] paths) throws IOException{
        if (paths == null) {
            System.exit(1);
        }
        int base = 1024;
        if (otherBase) {
            base = 1000;
        }
        int totalSize = 0;
        for (String filepath : paths) {
            Path path = Paths.get(filepath);
            int sizepath = size(path);
            if (totalSum) {
                totalSize += size(path);
            } else {
                if (usableFormat) {
                    String[] units = {"B", "KB", "MB", "GB"};
                    int i = 0;
                    while (sizepath > 0 && i < 3) {
                        sizepath /= base;
                        i++;
                    }
                    return ("Размер" + filepath + "равен" + sizepath + units[i]);
                } else {
                    return ("Размер" + filepath + "равен" + sizepath / base);
                }
            }
        }
        if (totalSum) {
            if (usableFormat) {
                String[] units = {"B", "KB", "MB", "GB"};
                int i = 0;
                while (totalSize > 0 && i < 3) {
                    totalSize /= base;
                    i++;
                }
                return ("Суммарный размер файлов равен" + totalSize + units[i]);
            } else {
                return ("Суммарный размер файлов равен" + totalSize / base);
            }
        }
        return "";
    }


}