package be.pxl.ja.opgave2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PassPhraseValidator<T> extends Thread {
    private final List<T> passPhrase;

    public PassPhraseValidator(List<T> asList) {
        passPhrase = asList;
    }

    public List<T> getPassPhrase() {
        return passPhrase;
    }

    public boolean isValid() {
        return ((Set<T>) new HashSet<>(passPhrase)).size() == passPhrase.size();
    }

    @Override
    public void run() {
        isValid();
    }
}
