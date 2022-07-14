

public class Professional extends Users {

	public Professional(int r, String name) {
		super(r, name);
	}

	public void print(String before, String later) {
		System.out.print(before);
		System.out.println("Name: " + name);
		System.out.println("Professional");
		System.out.print(later);
		
	}
	
	public String getType() {
		return "Professional";
	}

	@Override
	public String getId() {
		return null;
	}
}
