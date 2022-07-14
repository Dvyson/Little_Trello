

import java.util.LinkedList;

public abstract class Users implements Interface{
	
	public static final int Graduate_Student = 1; 	
	public static final int Mastering_Student = 2;
	public static final int PHD_Studant = 3; 	
	public static final int Teacher = 4;
	public static final int Researcher = 5;
	public static final int Professional = 6;
	public static final int Technician = 7;

	protected int r;
	protected String name;

	public Users(int r, String name) {
		this.r = r;
		this.name = name;
	}

	public abstract void print(String befora, String later);
	
	public abstract String getType();

	public int getR() {
		return r;
	}

	public String getName() {
		return name;
	}

	public void setRA(int id) {
		this.r = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static int IndexList(LinkedList<Users> list, int r) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getR() == r) {
				return i;
			}
		}
		
		return -1;
	}
}
