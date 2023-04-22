package model;


public class Timetable {
	private String tenLop;
	public String a[][] = new String[7][6];
	

	public void setTenLopTimetable(String s) {
		this.tenLop = s;
	}

	String getTenLopTimetable() {
		return this.tenLop;
	}
}
