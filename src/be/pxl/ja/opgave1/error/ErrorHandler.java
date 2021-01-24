package be.pxl.ja.opgave1.error;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class ErrorHandler {
    public <T extends Exception> void handle(Path path, Path error, T e) throws IOException {
        if (!Files.exists(error)) {
            if (Files.notExists(error.getParent())) {
                Files.createDirectory(error.getParent());
            }
            Files.createFile(error);
        }
        try (BufferedWriter writer = Files.newBufferedWriter(error)) {
            writer.write(String.format("%s - %s - %s",  LocalDateTime.now(), path.getFileName(), e.getMessage()));
        }
    }
}
