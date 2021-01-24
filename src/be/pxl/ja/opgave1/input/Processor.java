package be.pxl.ja.opgave1.input;

import be.pxl.ja.opgave1.Activity;
import be.pxl.ja.opgave1.ActivityTracker;
import be.pxl.ja.opgave1.ActivityType;
import be.pxl.ja.opgave1.CustomerRepository;
import be.pxl.ja.opgave1.error.CustomerNotFoundException;
import be.pxl.ja.opgave1.error.ErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Processor {
    CustomerRepository customerRepository = new CustomerRepository();
    ErrorHandler errorHandler = new ErrorHandler();

    default List<Activity> process(Path path, Path error) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            List<Activity> activities = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> fields = Arrays.asList(line.split(";"));
                String customerNumber = getCustomberNumber(fields);
                try {
                    if (customerRepository.getByCustomerNumber(customerNumber) == null) {
                        throw new CustomerNotFoundException(line);
                    }
                    activities.add(getActivity(fields, customerNumber));

                } catch (IllegalArgumentException | CustomerNotFoundException | DateTimeParseException e) {
                    errorHandler.handle(path, error, e);
                }

            }
            return activities;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    ;

    String getCustomberNumber(List<String> fields);

    LocalDate readDateOfActivity(String date);

    Activity getActivity(List<String> fields, String customerNumber);
}
