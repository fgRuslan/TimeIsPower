package xyz.vanillamc;

public class TimeImplementation {
	
	long hours, minutes, seconds;
	
	public TimeImplementation(long hours, long minutes, long seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public TimeImplementation() {
		
	}
	
	public void SetHours(long value) {
		hours = value;
	}
	public void SetMinutes(long value) {
		minutes = value;
	}
	public void SetSeconds(long value) {
		seconds = value;
	}

	public long GetHours() {
		return hours;
	}
	public long GetMinutes() {
		return minutes;
	}
	public long GetSeconds() {
		return seconds;
	}
}
