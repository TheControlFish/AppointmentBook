
public class Onetime extends Appointment {
	private int year;
	private int month;
	private int day;
	public Onetime(int y, int m, int d,int t, String des){
		super(t,des, "Onetime");
		year = y;
		month = m;
		day = d;
	}
	public boolean occursOn(int y, int m, int d){
		return (year == y && month == m && day == d);
	}
	public int year(){
		return year;
	}
	public int month(){
		return month;
	}
	public int day(){
		return day;
	}
	public String type(){
		return "Onetime";
	}
}
