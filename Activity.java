import java.time.LocalDateTime;
import java.util.LinkedList;

public class Activity {
	
	private int id;
	private String description;
	LocalDateTime startDate;
	LocalDateTime endDate;
	Users coordinator;
	LinkedList<Users> projectUsers;
	LinkedList<String> activies;
	
	public Activity(int id, String description, LocalDateTime startDate, LocalDateTime endDate, Users coordinator) {
		this.id = id;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.coordinator = coordinator;
		projectUsers = new LinkedList<>();
		activies = new LinkedList<>();
	}
	
	public void Add_Collaborator(Users user, String activie) {
		projectUsers.add(user);
		activies.add(activie);
	}
	
	public void Removing_Collaborator(int i) {
		projectUsers.remove(i);
		activies.remove(i);
	}
		
	public void print(String before, String later, boolean id_prin) {
		System.out.print(before);

		if(id_prin) {
			System.out.println("ID: " + id);
		}
		System.out.println("Description: " + description);
		System.out.println("Coordinator: " + coordinator.getName());
		System.out.println("Start Date: " + startDate.toString());
		System.out.println("End Date: " + endDate.toString());
		
		if(projectUsers.size() > 0) {
			printProfessionals();
		}
		
		System.out.print(later);
	}

	public void printProfessionals() {
		System.out.println("Professionals involved:");
		for(int i = 0; i < projectUsers.size(); i++) {
			System.out.println("\t -> " + projectUsers.get(i).getName() + " - " + projectUsers.get(i).getR());
			System.out.println("\t    Activies: " + activies.get(i));
		}
	}

	public int getId() {
		return id;
	}

	public Users getCoordinator() {
		return coordinator;
	}

	public LinkedList<Users> getProjectUsers() {
		return projectUsers;
	}
	
	public LinkedList<String> getActivies() {
		return activies;
	}

	public String getDescription() {
		return description;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public void setCoordinator(Users coordinator) {
		this.coordinator = coordinator;
	}
	
	
	
}
