package be.pxl.ja.opgave2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PassPhraseValidatorTest {
    @Test
    void name() {
        PassPhraseValidator<Integer> validIntegerPassPhrase = new PassPhraseValidator<>(Arrays.asList(12, 18, 15, 32));
        validIntegerPassPhrase.start();
        PassPhraseValidator<Integer> invalidIntegerPassPhrase = new PassPhraseValidator<>(Arrays.asList(12, 18, 15, 18, 32));
        invalidIntegerPassPhrase.start();
        PassPhraseValidator<String> validStringPassPhrase = new PassPhraseValidator<>(Arrays.asList("kat", "olifant", "wombat", "ooievaar"));
        validStringPassPhrase.start();
        PassPhraseValidator<String> invalidStringPassPhrase = new PassPhraseValidator<>(Arrays.asList("kat", "wombat", "kat", "olifant", "wombat", "ooievaar", "kat"));
        invalidStringPassPhrase.start();

        System.out.println(validIntegerPassPhrase.getPassPhrase() + " valid: " + validIntegerPassPhrase.isValid());

        System.out.println(invalidIntegerPassPhrase.getPassPhrase() + " valid: " + invalidIntegerPassPhrase.isValid());

        System.out.println(validStringPassPhrase.getPassPhrase() + " valid: " + validStringPassPhrase.isValid());

        System.out.println(invalidStringPassPhrase.getPassPhrase() + " valid: " + invalidStringPassPhrase.isValid());
    }

}
