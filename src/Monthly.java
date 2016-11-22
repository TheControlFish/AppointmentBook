
public class Monthly extends Appointment{
	private int day;
	public Monthly(int d, int t, String des){
		super(t, des, "Monthly");
		day = d;
	}
	public boolean occursOn(int y, int m, int d){
		return (day == d);
	}
	public int day(){
		return day;
	}
	public String type(){
		return "Monthly";
	}
}
