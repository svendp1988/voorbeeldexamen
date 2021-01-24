package be.pxl.ja.opgave1;

public enum ActivityTracker {
	ENDOMODO ("endomodo"),
	STRAVA ("strava");

	private final String label;

	ActivityTracker(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
