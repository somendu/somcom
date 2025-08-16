package j17.newapiexamples;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesMismatchExample {
    /**
     * Demonstrates comparing two files byte-by-byte using Files.mismatch()
     * Returns the index of first mismatch or -1 if files are identical.
     */
    public static void main(String[] args) throws Exception {
        Path file1 = Paths.get("file1.txt");
        Path file2 = Paths.get("file2.txt");

        long mismatchPos = Files.mismatch(file1, file2);
        if (mismatchPos == -1) {
            System.out.println("Files are identical.");
        } else {
            System.out.println("Files differ at byte position: " + mismatchPos);
        }
    }
}
