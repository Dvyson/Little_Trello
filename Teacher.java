

public class Teacher extends Users {

	public Teacher(int r, String name) {
		super(r, name);
	}

	public void print(String before, String later) {
		System.out.print(before);
		System.out.println("Name: " + name);
		System.out.println("Teacher");
		System.out.print(later);
		
	}
	
	public String getType() {
		return "Teacher";
	}


	@Override
	public String getId() {
		return null;
	}
}
