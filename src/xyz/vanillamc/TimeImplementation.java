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

	public TimeImplementation(long seconds) {
		this.seconds = seconds;
		this.minutes = seconds / 60;
		this.hours = this.minutes / 60;
	}

	public void SetHours(long value) {
		hours = value;
	}

	public void SetMinutes(long value) {
		if (value >= 60) {
			hours++;
			minutes = value - 60;
		} else
			minutes = value;
	}

	public void SetSeconds(long value) {
		if (value >= 60) {
			minutes++;
			seconds = value - 60;
		} else
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
