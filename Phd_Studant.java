

public class Phd_Studant extends Users {

	public Phd_Studant(int r, String name) {
		super(r, name);
	}

	public void print(String before, String later) {
		System.out.print(before);
		System.out.println("Name: " + name);
		System.out.println("PHD Stundant");
		System.out.print(later);
		
	}
	
	public String getType() {
		return "PHD Stundant";
	}

	@Override
	public String getId() {
		return null;
	}
}
