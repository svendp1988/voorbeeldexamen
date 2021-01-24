package be.pxl.ja.opgave1;

import be.pxl.ja.opgave1.error.CustomerNotFoundException;
import be.pxl.ja.opgave1.error.ErrorHandler;
import be.pxl.ja.opgave1.error.InvalidFilenameException;
import be.pxl.ja.opgave1.input.Processor;
import be.pxl.ja.opgave1.input.ProcessorFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class ActivityProcessor {

    ErrorHandler errorHandler;
    private static final String INVALID_FILENAME_ERROR_MSG = "INVALID FILENAME";

    public ActivityProcessor() {
        errorHandler = new ErrorHandler();
    }


    public List<Activity> processActivities(Path activityFile, Path errorFile) throws IOException {
        Optional<ActivityTracker> activityTracker = determineActivityTracker(activityFile);
        try {
            if (activityTracker.isEmpty()) {
                throw new InvalidFilenameException();
            }
            ActivityTracker tracker = activityTracker.get();
            Processor processor = ProcessorFactory.get(tracker);
            return processor.process(activityFile, errorFile);
        } catch (CustomerNotFoundException | InvalidFilenameException e) {
            errorHandler.handle(activityFile, errorFile, e);
        }
        return Collections.emptyList();
    }

    private Optional<ActivityTracker> determineActivityTracker(Path activityFile) {
        return Arrays.stream(ActivityTracker.values())
                .filter(activityTracker -> activityFile.getFileName().toString().contains(activityTracker.getLabel()))
                .findAny();
    }
}
