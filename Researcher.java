

public class Researcher extends Users {

	public Researcher(int r, String name) {
		super(r, name);
	}

	public void print(String before, String later) {
		System.out.print(before);
		System.out.println("Name: " + name);
		System.out.println("Researcher");
		System.out.print(later);
		
	}
	
	public String getType() {
		return "Researcher";
	}

	@Override
	public String getId() {
		return null;
	}
}
