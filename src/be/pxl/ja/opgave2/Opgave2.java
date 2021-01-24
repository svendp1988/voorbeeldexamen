package be.pxl.ja.opgave2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Opgave2 {
    public static void main(String[] args) {
        Path path = Path.of(System.getProperty("user.dir"), "resources", "opgave2", "passphrases.txt");
        List<List<String>> passPhrases = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while((line = reader.readLine()) != null) {
                passPhrases.add(Arrays.asList(line.split(" ")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<PassPhraseValidator<String>> list = new ArrayList<>(passPhrases.size());
        for (List<String> passPhrase : passPhrases) {
            PassPhraseValidator<String> worker = new PassPhraseValidator<>(passPhrase);
            worker.start();
            list.add(worker);
        }
        int validPassphrases = 0;
        for (PassPhraseValidator<String> worker : list) {
            try {
                worker.join();

                if (worker.isValid()) validPassphrases++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Aantal geldige paswoordzinnen: " + validPassphrases);

    }
}

