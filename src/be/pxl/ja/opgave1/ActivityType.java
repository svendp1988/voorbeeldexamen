package be.pxl.ja.opgave1;

public enum ActivityType {
	RUNNING(3), RIDING(1), SWIMMING(5);
	
	private int pointsPerKm;
	
	private ActivityType(int pointsPerKm) {
		this.pointsPerKm = pointsPerKm;
	}
	
	public int getPointsPerKm() {
		return pointsPerKm;
	}
}
