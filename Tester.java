import java.io.*;
import java.util.*;
public class Tester {
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		ArrayList<Appointment> apmts = new ArrayList<Appointment>();
		String s = "";
		while (!s.equalsIgnoreCase("EXIT")){
			Scanner kb = new Scanner(System.in);
			System.out.print("Reload data from the file (type \"R\"),\nadd an apointment (type \"A\"),\nor check a date for appointments (type \"C\")\n(type \"save\" to save the appointment data to a file, type \"exit\" to end the program):");
			s = kb.nextLine();
			System.out.println();
			if (s.equalsIgnoreCase("R")){
				reload(apmts);
			}
			else if (s.equalsIgnoreCase("A")){
				add(apmts);
			}
			else if (s.equalsIgnoreCase("C")){
				ArrayList<Appointment> occurs = check(apmts);
				System.out.println("\nAppointments on selected date: ");
				for(int n = 0; n < occurs.size(); n++){
					System.out.println("\t" + ((Appointment)occurs.get(n)).description() + " at " + ((Appointment)occurs.get(n)).time());
				}
				System.out.println();
			}
			else if (s.equalsIgnoreCase("SAVE")){
				save(apmts);
				System.out.println("Saved");
				System.out.println();
			}
			else if (s.equalsIgnoreCase("EXIT")){
				System.out.println("Ending Program");
				break;
			}
			else {
				System.out.println("Not Valid");
			}
		}
	}
	public static void reload(ArrayList reloading) throws IOException{
		int day, time;
		Scanner fr = new Scanner(new File("E:\\AP Comp Sci\\Appointment Book\\src\\Appointments.txt"));
		while (fr.hasNext()){
			String type = fr.nextLine();
			String des = fr.nextLine();
			if (type.equals("Onetime")){
				int year = fr.nextInt();
				int month = fr.nextInt();
				day = fr.nextInt();
				time = fr.nextInt();
				reloading.add(new Onetime(year,month,day,time,des));
			}
			else if (type.equals("Monthly")){
				day = fr.nextInt();
				time = fr.nextInt();
				reloading.add(new Monthly(day,time,des));
			}
			else if (type.equals("Daily")) {
				time = fr.nextInt();
				reloading.add(new Daily(time,des));
			}
			else {
				System.out.println("Reloaded Appointment Data");
				System.out.println();
			}
		}
	}
	public static void add(ArrayList adding){
		Scanner kb = new Scanner(System.in);
		int year;
		int month;
		int time;
		int day;
		String des;
		System.out.print("What type of appointment would you like to add? (\"OneTime\", \"Daily\", or \"Monthly\")");
		String type = kb.nextLine();
		if (type.equalsIgnoreCase("ONETIME")){
			System.out.print("Type the description: ");
			des = kb.nextLine();
			System.out.print("Type the year: ");
			year = kb.nextInt();
			System.out.print("Type the month (ie.\"1\" for Junuary): ");
			month = kb.nextInt();
			System.out.print("Type the day: ");
			day = kb.nextInt();
			System.out.print("Type the time (in military time): ");
			time = kb.nextInt();
			adding.add(new Onetime(year,month,day,time,des));
			System.out.println("\nAppointment Added");
		}
		else if (type.equalsIgnoreCase("MONTHLY")){
			System.out.print("Type the description: ");
			des = kb.nextLine();
			System.out.print("Type the day: ");
			day = kb.nextInt();
			System.out.print("Type the time (in military time): ");
			time = kb.nextInt();
			adding.add(new Monthly(day,time,des));
			System.out.println("\nAppointment Added");
		
		}
		else if (type.equalsIgnoreCase("Daily")){
			System.out.print("Type the description: ");
			des = kb.nextLine();
			System.out.print("Type the time (in military time): ");
			time = kb.nextInt();
			adding.add(new Daily(time,des));
			System.out.println("\nAppointment Added");
		}
		else {
			System.out.println("Invalid Type");
		}
		System.out.println();
	}
	public static ArrayList check(ArrayList checking){
		ArrayList<Appointment> occurs = new ArrayList<Appointment>();
		Scanner kb = new Scanner(System.in);
		System.out.print("Type the year to check: ");
		int year = kb.nextInt();
		System.out.print("Type the month to check: ");
		int month = kb.nextInt();
		System.out.print("Type the day to check: ");
		int day = kb.nextInt();
		for (int n = 0; n < checking.size(); n++){
			String type = ((Appointment)checking.get(n)).type();
			Appointment a;
			if (type.equals("Onetime")){
				if (((Onetime) checking.get(n)).occursOn(year,month,day)){
					occurs.add((Appointment)checking.get(n));
				}
			}
			if (type.equals("Monthly")){
				if (((Monthly) checking.get(n)).occursOn(year,month,day)){
					occurs.add((Appointment)checking.get(n));
				}
			}
			if (type.equals("Daily")){
				if (((Daily) checking.get(n)).occursOn(year,month,day)){
					occurs.add((Appointment)checking.get(n));
				}
			}
			
		}
		return occurs;
	}
	public static void save(ArrayList saving) throws IOException {
		FileWriter fw = new FileWriter("E:\\AP Comp Sci\\Appointment Book\\src\\Appointments.txt");
		PrintWriter output = new PrintWriter(fw);
		for (int n = 0; n < saving.size(); n++){
			Appointment a = (Appointment)saving.get(n);
			String type = a.type();
			if (type.equals("Onetime")){
				output.println(type);
				output.println(a.description());
				output.println(((Onetime) a).year());
				output.println(((Onetime)a).month());
				output.println(((Onetime)a).day());
				output.println(a.time());
			}
			else if (type.equals("Monthly")){
				output.println(type);
				output.println(a.description());
				output.println(((Monthly)a).day());
				output.println(a.time());
			}
			else {
				output.println(type);
				output.println(a.description());
				output.println(a.time());
			}
		}
		output.close();
		fw.close();
	}
}
