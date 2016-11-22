
public class Daily extends Appointment {
	public Daily(int t, String des){
		super(t, des, "Daily");
	}
	public boolean occursOn(int y, int m, int d){
		return true;
	}
	public String type(){
		return "Daily";
	}
}
