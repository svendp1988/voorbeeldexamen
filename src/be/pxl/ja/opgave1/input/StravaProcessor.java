package be.pxl.ja.opgave1.input;

import be.pxl.ja.opgave1.Activity;
import be.pxl.ja.opgave1.ActivityTracker;
import be.pxl.ja.opgave1.ActivityType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StravaProcessor implements Processor {
    @Override
    public String getCustomberNumber(List<String> fields) {
        return Arrays.stream(fields.get(0).split(" "))
                .filter(elt -> {
                    try {
                        Integer.parseInt(elt);
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .collect(Collectors.joining());
    }

    @Override
    public LocalDate readDateOfActivity(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }

    @Override
    public Activity getActivity(List<String> fields, String customerNumber) {
        Activity activity = new Activity();
        activity.setCustomerNumber(customerNumber);
        activity.setActivityDate(readDateOfActivity(fields.get(1)));
        activity.setActivityType(ActivityType.valueOf(fields.get(2).toUpperCase()));
        activity.setDistance((ActivityType.SWIMMING.equals(activity.getActivityType()) ? Double.parseDouble(fields.get(3)) / 1000.0 : Double.parseDouble(fields.get(3))));
        activity.setTracker(ActivityTracker.STRAVA);
        return activity;
    }
}
