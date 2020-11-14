public class Lesson {
	private String type;
	private String location;
	private String dayOfWeek;
	private int time;
	private int duration;
	private int[] lessonWeek;
	
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type=type;
	}
	
	public String getLocation() {
		return this.location;
	}
	public void setLocation(String location) {
		this.location=location;
	}
	
	public String getDayOfWeek() {
		return this.dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek=dayOfWeek;}
	
	public int getTime() {
		return this.time;
	}
	public void setTime(int time) {
		this.time=time;
	}
	
	public int getDuration() {
		return this.duration;
	}
	public void setDuration(int duration) {
		this.duration=duration;
	}
	
	public int[] getLessonWeek() {
		return this.lessonWeek;
	}
	public void setLessonWeek(int[] lessonWeek) {
		this.lessonWeek=lessonWeek;
	}
}
