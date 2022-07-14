

public class Technician extends Users {

	public Technician(int r, String name) {
		super(r, name);
	}

	public void print(String before, String later) {
		System.out.print(before);
		System.out.println("Name: " + super.name);
		System.out.println("Technician");
		System.out.print(later);
		
	}
	
	public String getType() {
		return "Technician";
	}


	@Override
	public String getId() {
		return null;
	}
}
