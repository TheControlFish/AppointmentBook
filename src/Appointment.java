
public abstract class Appointment {
	private int time;
	private String type;
	private String description;
	public Appointment(int t, String des, String ty){
		time = t;
		description = des;
	}
	public String description(){
		return description;
	}
	public int time(){
		return time;
	}
	public String type(){
		return type;
	}
}
