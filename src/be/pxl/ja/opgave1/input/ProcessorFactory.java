package be.pxl.ja.opgave1.input;

import be.pxl.ja.opgave1.ActivityTracker;

public class ProcessorFactory {
    public static Processor get(ActivityTracker tracker) {
        switch (tracker) {
            case STRAVA:
                return new StravaProcessor();
            case ENDOMODO:
                return new EndomondoProcessor();
            default:
                return null;
        }
    }
}
