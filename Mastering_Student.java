

public class Mastering_Student extends Users {

	public Mastering_Student(int r, String name) {
		super(r, name);
	}

	public void print(String before, String later) {
		System.out.print(before);
		System.out.println("Name: " + name);
		System.out.println("Matering Stundant");
		System.out.print(later);
		
	}
	
	public String getType() {
		return "Matering Stundant";
	}

	@Override
	public String getId() {
		return null;
	}
}
