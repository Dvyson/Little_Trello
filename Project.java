import java.time.LocalDateTime;
import java.util.LinkedList;

public class Project {
	
	public static final int Status_Created = 1;
	public static final int Status_Started = 2;
	public static final int Status_In_Progress = 3;
	public static final int Status_Completed = 4;
	
	private int id;
	private String description;
	private LocalDateTime start;
	private LocalDateTime end;
	private Users coordinator;
	private LinkedList<Users> projectUsers;
	private LinkedList<Activity> activities;
	private int status;

	public Project(int id) {
		this.id = id;
		status = Project.Status_Created;
		description = null;
		coordinator = null;
		start = null;
		end = null;
		projectUsers = new LinkedList<>();
		activities = new LinkedList<>();
	}

	public static boolean True_Coordinator(Users u) {
		if (u.getClass() == Researcher.class || u.getClass() == Teacher.class) {
			return true;
		} else {
			return false;
		}
	}

	public void Adding_Collaborator(Users u) {
		if(!projectUsers.contains(u)) {
			projectUsers.add(u);
		}
	}

	public void Adding_Activies(Activity a) {
		if(!activities.contains(a)) {
			activities.add(a);
		}
	}

	public boolean Removing_Collaborator(Users u) {
		return projectUsers.remove(u);
	}

	public boolean Removing_Activies(Activity a) {
		return activities.remove(a);
	}

	public void Print_Activies(String before, String later, boolean myID) {
		System.out.print(before);
		if(myID) {
			System.out.println("\nID: " + id);
		}
		
		System.out.println("\nStatus: " + getStatusT());
		
		if(coordinator == null) {
			System.out.println("\nUndefined");
		} else {
			System.out.println("\nCoordinator " + coordinator.getName());
		}
		
		if(description == null) {
			System.out.println("\nUndefined");
		} else {
			System.out.println("\nDescription " + description);
		}
		
		if(start == null) {
			System.out.println("\nUndefined!");
		} else {
			System.out.println("\nStart Date: " + start.toString());
		}
		
		if(end == null) {
			System.out.println("\nUndefined!");
		} else {
			System.out.println("\nEnd Date: " + end.toString());
		}
		
		System.out.println("\nProfessionals involved in the project:");
		for(Users u : projectUsers) {
			u.print(" -> ", "");
		}
		
		System.out.println("Project activities:");
		for(Activity a : activities) {
			a.print(" -> ", "", false);
		}
		
		System.out.print(later);
	}

	
	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public Users getCoordinator() {
		return coordinator;
	}

	public LinkedList<Users> getProjectUsers() {
		return projectUsers;
	}

	public LinkedList<Activity> getActivies() {
		return activities;
	}
	
	public int getStatus() {
		return status;
	}
	
	public String getStatusT() {
		switch(status) {
		case Project.Status_Created:
			return "In progress";
		case Project.Status_Started:
			return "Started";
		case Project.Status_In_Progress:
			return "In progress";
		case Project.Status_Completed:
			return "Concluded";
		}
		return null;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setDescription(String descrição) {
		this.description = descrição;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public void setCoordinator(Users coordinator) {
		this.coordinator = coordinator;
	}
	
	
	
}
