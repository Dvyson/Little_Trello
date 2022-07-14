

public class Graduate_Student extends Users {

	public Graduate_Student(int r, String name) {
		super(r, name);
	}

	public void print(String before, String later) {
		System.out.print(before);
		System.out.println("Name: " + name);
		System.out.println("Graduate Stundant");
		System.out.print(later);
		
	}
	
	public String getType() {
		return "Graduate Stundant";
	}

	@Override
	public String getId() {
		return null;
	}
}
