package be.pxl.ja.opgave1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ActivityProcessorTest {
    Path correctStravaPath = Path.of(System.getProperty("user.dir"), "resources", "opgave1", "activities_from_strava.txt");
    Path correctEndomondoPath = Path.of(System.getProperty("user.dir"), "resources", "opgave1", "endomodo_activities.txt");
    Path incorrectPath = Path.of(System.getProperty("user.dir"), "resources", "opgave1", "runkeeper_activities.txt");
    Path errorFile = Path.of(System.getProperty("user.dir"), "resources", "opgave1", "error_file.txt");
    ActivityProcessor activityProcessor;

    @BeforeEach
    void setUp() {
        activityProcessor = new ActivityProcessor();
    }

    @Test
    void shouldReturnEmptyList_ifPathIncorrect() throws IOException {
        assertEquals(Collections.emptyList(), activityProcessor.processActivities(incorrectPath, errorFile));
        assertTrue(Files.exists(errorFile));
    }

    @Test
    void shouldProcessStravaFiles() throws IOException {
        assertFalse(activityProcessor.processActivities(correctStravaPath, errorFile).isEmpty());
    }

    @Test
    void shouldProcessEndomondoFiles() throws IOException {
        assertFalse(activityProcessor.processActivities(correctEndomondoPath, errorFile).isEmpty());
    }
}
