import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

public class Trello {
	
	private static LinkedList<Project> projects = new LinkedList<>();
	private static LinkedList<Users> users = new LinkedList<>();
	private static LinkedList<Activity> activities = new LinkedList<>();
	private static int project_id = 0;
	private static int activie_id = 0;
	
	
	private static String Scan() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static int Read_Option(int [] options) {
		int input;
		while(true) {
			System.out.print("Choose an option:");
			try {
				input = Integer.parseInt(Scan());
			} catch (NumberFormatException e) {
				System.out.println("\nWrong. Try again!\n");
				continue;
			}
			for(int o : options) {
				if(input == o) {
					return input;
				}
			}
			System.out.println("\nWrong!\n");
		}
	}
	
	private static int Read_Int(int minimum, int maximum) {
		int input;

		while(true) {
			try {
				input = Integer.parseInt(Scan());
			} catch (NumberFormatException e) {
				return minimum-1;
			}
			if(input < minimum || input > maximum) {
				return maximum+1;
			}
			return input;
		}
	}
	
	private static int Read_ID(String label) {
		int r;

		while(true) {
			System.out.print(label + ": ");
			try {
				r = Integer.parseInt(Scan());

			} catch (NumberFormatException e) {
				System.out.println("\n" + label + " invalid!\n");
				continue;
			}
			return r;
		}	
	}

	private static LocalDateTime Read_Date() {
		LocalDate date;
		LocalTime time;
		int day, month, year, hour, minutes;

		while (true) {

			while(true) {
				System.out.print("Day: ");
				day = Read_Int(1,31);
				
				if(day == 0 || day == 32) {
					System.out.print("\nInvalid day! Enter a number between 1 and 31.\n");
				} else {
					break;
				}
			}

			while(true) {
				System.out.print("Month: ");
				month = Read_Int(1,12);
				
				if(month == 0 || month == 13) {
					System.out.print("\nInvalid month! Enter a number between 1 and 12.\n");
				} else {
					break;
				}
			}

			while(true) {
				System.out.print("Year: ");
				year = Read_Int(2000,2100);
				
				if(year == 1999 || year == 2101) {
					System.out.print("\nInvalid year!\n");
				} else {
					break;
				}
			}

			try {
				date = LocalDate.of(year, month, day);
				break;
			} catch (DateTimeException e) {
				System.out.println("\nInvalid Date!\n");
				continue;
			}
		}

		while(true) {
			while(true) {
				System.out.print("Hour: ");
				hour = Read_Int(0,23);
				
				if(hour == -1 | hour == 24) {
					System.out.print("\nInvalid time! Enter a number between 0 and 23.\n");
				} else {
					break;
				}
			}
			while(true) {
				System.out.print("Minutes: ");
				minutes = Read_Int(0,59);
				
				if(minutes == -1 || minutes == 60) {
					System.out.print("\nInvalid minute! Enter a number between 0 and 59.\n");
				} else {
					break;
				}
			}
			try {
				time = LocalTime.of(hour, minutes);
				break;
			} catch (DateTimeException e) {
				System.out.println("\nInvalid Date!\n");
				continue;
			}
		}
		return LocalDateTime.of(date, time);
	}

	private static int Menu() {
		System.out.println("Welcome to Trello\n");
		System.out.println("Choose an option:\n5 - Close\n1 - Projects\n2 - Users\n3 - Activies\n4 - Reports");
						
		return Read_Option(new int[]{1,2,3,4,5});
	}

	private static void Projects_Menu() {
		while(true) {
			System.out.println("Projects\n");
			System.out.println("Choose an option:\n4 - Close\n1 - New Project\n2 - Modify\n3 - Consult\n");

			switch(Read_Option(new int[]{1,2,3,4})) {
			case 1:
				New_Project();
				break;
			case 2:
				Modify_Project();
				break;
			case 3:
				Consult_Projects();
				break;
			case 4:
				System.out.println("");
				return;
			}
		}
		
	}
	private static void New_Project() {
		Project p = new Project(project_id++);
		
		while(true) {
			System.out.println("New Project\n");
			System.out.println("Choose an option:\n9 - Close\n1 - Description\n2 - Coordinator\n3 - Professionals\n4 - Activies\n5 - Started\n6 - End\n7 - Status\n8 - Create project and go back\n");
			switch(Read_Option(new int[]{1,2,3,4,5,6,7,8,9})) {
			case 1:
				Modify_Project_Description(p);
				break;
			case 2:
				Modify_Coordinator(p);
				break;
			case 3:
				Project_Professionals_Submenu(p);
				break;
			case 4:
				Submenu_Activities_Project(p);
				break;
			case 5:
				Modify_Start_Date(p);
				break;
			case 6:
				Modify_End_Date(p);
				break;
			case 7:
				Modify_Status(p);
				break;
			case 8:
				projects.add(p);
				System.out.println("Project created!");
				return;
			case 9:
				System.out.println("Operation canceled!");
				return;
			}
			
		}
		
		
	}
	
	private static void Modify_Project() {
		if(projects.size() == 0) {
			System.out.println("There are no projects to modify!\n");
			return;
		}

		System.out.println("\nModify Project");
		
		System.out.println("\nProjects:");
		for(Project p : projects) {
		}

		while(true) {
			int id = Read_ID("ID project");
			Project proj = null;
			boolean encontrado = false;
			
			for(Project p : projects) {
				if (p.getId() == id) {
					encontrado = true;
					proj = p;
					break;
				}
			}
			
			if(encontrado) {
				proj.Print_Activies("\n----------------------------------------------\n",
						"----------------------------------------------\n", true);
				
				while(true) {
					System.out.println("\nModify Project");
					System.out.println("Choose an option:\n8 - Close\n1 - Description\n2 - Coordinator\n3 - Professionals\n4 - Activies\n5 - Started\n6 - End\n7 - Status\n");
					switch(Read_Option(new int[]{1,2,3,4,5,6,7,8})) {
					case 1:
						Modify_Project_Description(proj);
						break;
					case 2:
						Modify_Coordinator(proj);
						break;
					case 3:
						Project_Professionals_Submenu(proj);
						break;
					case 4:
						Submenu_Activities_Project(proj);
						break;
					case 5:
						Modify_Start_Date(proj);
						break;
					case 6:
						Modify_End_Date(proj);
						break;
					case 7:
						Modify_Status(proj);
						break;
					case 8:
						System.out.println("Operation cancel!");
						return;
					}
				}
			} else {
				System.out.println("\nUndefined!");
			}
		}
	}
	
	private static void Consult_Projects() {
		if(projects.size() == 0) {
			System.out.println("Undefined!\n");
			return;
		}
		System.out.println("\nConsult Projects");
		System.out.println("Choose an option:\n1 - Project List\n2 - Search for ID");

		switch(Read_Option(new int[] {1,2})) {
		case 1:

			System.out.println("\nProject List");
			System.out.println("----------------------------------------------");
			for(Project p : projects) {
				p.Print_Activies("", "----------------------------------------------\n", false);
			}
			return;
		case 2:
			System.out.println("\nSearch for ID");
			int id = Read_ID("ID");

			for(Project p : projects) {
				
				if(p.getId() == id) {
					p.Print_Activies("\n----------------------------------------------\n",
							"----------------------------------------------\n", false);
					return;
				}				
			}
			
			System.out.println("\nUndefined!");
			
			return;
		}
		
	}

	private static void Project_Professionals_Submenu(Project p) {
		while(true) {
			System.out.println("\nProject professionals");
			System.out.println("Choose an option:\n1 - Add\n2 - Remove\n 3 - List\n4 - Close");

			switch(Read_Option(new int[]{1,2,3,4})) {
			case 1:
				Adding_Professional(p);
				break;
			case 2:
				Removing_Professional(p);
				break;
			case 3:
				List_Professionals(p);
				break;
			case 4:
				return;
			}
			
		}
	}
	
	private static void Submenu_Activities_Project(Project p) {
		while(true) {
			System.out.println("\nAtividades do projeto");
			System.out.println("1 - Adicionar");
			System.out.println("2 - Remover");
			System.out.println("3 - Listar");
			System.out.println("4 - Voltar");
			switch(Read_Option(new int[]{1,2,3,4})) {
			case 1:
				Adding_Activies(p);
				break;
			case 2:
				Removing_Activies(p);
				break;
			case 3:
				List_Activies(p);
				break;
				case 4:
				return;
			}
			
		}
	}
	
	private static void Modify_Project_Description(Project p) {
		String description;
		
		if(p.getDescription() == null) {
			System.out.println("\nDescription do Project");

			System.out.println("Description: ");
			description = Scan();
			
			p.setDescription(description);
			System.out.println("\nDescription sucess!");
			return;
						
		} else {
			System.out.println("\nModify Descri????o");

			System.out.println("New Descrition: ");
			description = Scan();

			System.out.println("\nDeseja substituir a descri????o?");
			
			System.out.println("\nDescri????o antiga: " + p.getDescription());
			
			System.out.println("Nova descri????o: " + description);
			
			System.out.println("\n1 - Confirmar e substituir");
			System.out.println("2 - Cancelar e voltar");

			switch(Read_Option(new int[] {1,2})) {
			case 1:
				p.setDescription(description);
				System.out.println("\nDescri????o alterada com sucesso!");
				return;
			case 2:
				System.out.println("\nOpera????o cancelada!");
				return;
			}
		}
	}
	
	private static void Modify_Coordinator(Project p) {
		
		if(p.getCoordinator() == null) {
			System.out.println("\nCoordenador de Project");

			while(true) {
				int ra = Read_ID("RA do Coordenador");
				Users user = null;
				boolean encontrado = false;

				for(Users u : users) {
					if(u.getR() == ra) {
						encontrado = true;
						user = u;
						break;
					}
				}

				if(encontrado) {
					user.print("\n----------------------------------------------\n",
							"----------------------------------------------\n");

					if(Project.True_Coordinator(user)) {
						System.out.println("\nConfirma este usu??rio como coordenador do projeto?");
						
						System.out.println("\n1 - Confirmar");
						System.out.println("2 - Inserir outro RA");
						System.out.println("3 - Cancelar e voltar");
						
						switch(Read_Option(new int[] {1,2,3})) {
						case 1:
							p.setCoordinator(user);
							System.out.println("\nCoordenador inclu??do com sucesso!");
							return;
						case 2:
							continue;
						case 3:
							System.out.println("\nOpera????o cancelada!");
							return;
						}					
					} else {
						System.out.println("\nO usu??rio encontrado n??o pode ser coordenador, pois n??o ?? Teacher nem Researcher!");
						
						System.out.println("\nDeseja cadastrar um novo usu??rio?");
						
						System.out.println("\n1 - Cadastrar novo usuario");
						System.out.println("2 - Inserir outro RA");
						System.out.println("3 - Cancelar e voltar");
						
						switch(Read_Option(new int[] {1,2,3})) {
						case 1:
							cadastrarUsuario();
							continue;
						case 2:
							continue;
						case 3:
							System.out.println("\nOpera????o cancelada!");
							return;
						}
					}					
				} else {
					System.out.println("\nRA do Coordenador n??o encontrado!");
					
					System.out.println("\nDeseja cadastrar um novo usu??rio?");
					
					System.out.println("1 - Cadastrar novo usuario");
					System.out.println("2 - Inserir outro RA");
					System.out.println("3 - Cancelar e voltar");
					
					switch(Read_Option(new int[] {1,2,3})) {
					case 1:
						cadastrarUsuario();
						continue;
					case 2:
						continue;
					case 3:
						System.out.println("\nOpera????o cancelada!");
						return;
					}
				}
			}						
		} else {
			System.out.println("\nAlterar Coordenador de Project");
			
			while(true) {
				int ra = Read_ID("RA do Novo Coordenador");
				Users user = null;
				boolean encontrado = false;
				
				for(Users u : users) {
					if(u.getR() == ra) {
						encontrado = true;
						user = u;
						break;
					}
				}
				
				if(encontrado) {
					p.getCoordinator().print("\n----------------------------------------------\nAntigo Coordenador:\n",
							"----------------------------------------------\n");
					
					user.print("Novo Coordenador:\n",
							"----------------------------------------------\n");
					
					if(Project.True_Coordinator(user)) {
						System.out.println("\nConfirmar a substitui????o do coordenador do projeto?");
						
						System.out.println("\n1 - Confirmar");
						System.out.println("2 - Inserir outro RA");
						System.out.println("3 - Cancelar e voltar");
						
						switch(Read_Option(new int[] {1,2,3})) {
						case 1:
							p.setCoordinator(user);
							System.out.println("\nCoordenador substituido com sucesso!");
							return;
						case 2:
							continue;
						case 3:
							System.out.println("\nOpera????o cancelada!");
							return;
						}					
					} else {
						System.out.println("\nO usu??rio encontrado n??o pode ser coordenador, pois n??o ?? Teacher nem Researcher!");
						
						System.out.println("\nDeseja cadastrar um novo usu??rio?");
						
						System.out.println("\n1 - Cadastrar novo usuario");
						System.out.println("2 - Inserir outro RA");
						System.out.println("3 - Cancelar e voltar");
						
						switch(Read_Option(new int[] {1,2,3})) {
						case 1:
							cadastrarUsuario();
							continue;
						case 2:
							continue;
						case 3:
							System.out.println("\nOpera????o cancelada!");
							return;
						}
					}					
				} else {
					System.out.println("RA do Novo Coordenador n??o encontrado!");
					
					System.out.println("\nDeseja cadastrar um novo usu??rio?");
					
					System.out.println("\n1 - Cadastrar novo usuario");
					System.out.println("2 - Inserir outro RA");
					System.out.println("3 - Cancelar e voltar");
					
					switch(Read_Option(new int[] {1,2,3})) {
					case 1:
						cadastrarUsuario();
						continue;
					case 2:
						continue;
					case 3:
						System.out.println("\nOpera????o cancelada!");
						return;
					}
				}
			}	
		}
	}
	
	private static void Modify_Start_Date(Project p) {
		LocalDateTime inicio;
		
		if(p.getStart() == null) {
			System.out.println("\nData de In??cio do Project");

			System.out.println("Data de in??cio: ");
			inicio = Read_Date();
			
			p.setStart(inicio);
			System.out.println("\nData de in??cio inclu??da com sucesso!");
			return;

		} else {
			System.out.println("\nAlterar Data de In??cio de Project");

			System.out.println("Nova data de in??cio:");
			inicio = Read_Date();

			System.out.println("\nDeseja substituir a data de in??cio?");
			
			System.out.println("\nData de in??cio antigo: " + p.getStart().toString());
			
			System.out.println("Nova data de in??cio: " + inicio.toString());
			
			System.out.println("\n1 - Confirmar e substituir");
			System.out.println("2 - Cancelar e voltar");


			switch(Read_Option(new int[] {1,2})) {
			case 1:
				p.setStart(inicio);
				System.out.println("\nData de in??cio alterada com sucesso!");
				return;
			case 2:
				System.out.println("\nOpera????o cancelada!");
				return;
			}
		}		
	}
	
	private static void Modify_End_Date(Project p) {
		LocalDateTime termino;
		
		if(p.getEnd() == null) {
			System.out.println("\nData de T??rmino do Project");

			System.out.println("Data de t??rmino: ");
			termino = Read_Date();
			
			p.setEnd(termino);
			System.out.println("\nData de t??rmino inclu??da com sucesso!");
			return;
						
		} else {
			System.out.println("\nAlterar Data de T??rmino de Project");

			System.out.println("Nova data de t??rmino:");
			termino = Read_Date();

			System.out.println("\nDeseja substituir a data de t??rmino?");
			
			System.out.println("\nData de t??rmino antigo: " + p.getEnd().toString());
			
			System.out.println("Nova data de t??rmino: " + termino.toString());
			
			System.out.println("\n1 - Confirmar e substituir");
			System.out.println("2 - Cancelar e voltar");

			switch(Read_Option(new int[] {1,2})) {
			case 1:
				p.setEnd(termino);
				System.out.println("\nData de t??rmino alterada com sucesso!");
				return;
			case 2:
				System.out.println("\nOpera????o cancelada!");
				return;
			}
		}		
	}
	
	private static void Adding_Professional(Project p) {
		while(true) {
			System.out.println("\nAdicionar Professional ao Project");
			
			int ra = Read_ID("RA");
			Users user = null;
			boolean encontrado = false;

			for(Users u : users) {
				if(u.getR() == ra) {
					encontrado = true;
					user = u;
					break;
				}
			}

			if(encontrado) {
				user.print("\n----------------------------------------------\n",
						"----------------------------------------------\n");

				System.out.println("\nConfirma inclus??o deste usu??rio ao projeto?");
				
				System.out.println("\n1 - Confirmar");
				System.out.println("2 - Inserir outro RA");
				System.out.println("3 - Cancelar e voltar");
				
				switch(Read_Option(new int[] {1,2,3})) {
				case 1:
					p.Adding_Collaborator(user);
					System.out.println("\nProfessional inserido ao projeto com sucesso!");
					return;
				case 2:
					continue;
				case 3:
					System.out.println("\nOpera????o cancelada!");
					return;
				}
			} else {
				System.out.println("\nRA n??o encontrado!");
				
				System.out.println("\nDeseja cadastrar um novo usu??rio?");
				
				System.out.println("\n1 - Cadastrar novo usuario");
				System.out.println("(2 - Inserir outro RA");
				System.out.println("(3 - Cancelar e voltar");
				
				switch(Read_Option(new int[] {1,2,3})) {
				case 1:
					cadastrarUsuario();
					continue;
				case 2:
					continue;
				case 3:
					System.out.println("\nOpera????o cancelada!");
					return;
				}
			}
		}
	}
	
	private static void Removing_Professional(Project p) {
		System.out.println("\nRemover Professional ao Project");

		while(true) {
			int ra = Read_ID("RA");
			Users user = null;
			boolean encontrado = false;

			for(Users u : users) {
				if(u.getR() == ra) {
					encontrado = true;
					user = u;
					break;
				}
			}

			if(encontrado) {
				user.print("\n----------------------------------------------\n",
						"----------------------------------------------\n");

				System.out.println("\nConfirma exclus??o deste usu??rio ao projeto?");
				
				System.out.println("\n1 - Confirmar exclus??o");
				System.out.println("2 - Inserir outro RA");
				System.out.println("3 - Cancelar e voltar");
				
				switch(Read_Option(new int[] {1,2,3})) {
				case 1:
					p.Removing_Collaborator(user);
					System.out.println("\nProfessional removido com sucesso!");
					return;
				case 2:
					continue;
				case 3:
					System.out.println("\nOpera????o cancelada!");
					return;
				}
			} else {
				System.out.println("RA n??o encontrado!");
				
				System.out.println("\n1 - Inserir outro RA");
				System.out.println("2 - Cancelar e voltar");
				
				switch(Read_Option(new int[] {1,2})) {
				case 1:
					continue;
				case 2:
					System.out.println("\nOpera????o cancelada!");
					return;
				}
			}
		}
	}
	
	private static void List_Professionals(Project p) {
		System.out.println("\n== Lista de Profissionais do projeto ==");
		
		System.out.println("----------------------------------------------");
		for(Users u : p.getProjectUsers()) {
			u.print("","----------------------------------------------\n");
		}
		
		return;
	}
	
	private static void Adding_Activies(Project p) {
		while(true) {
			System.out.println("\nAdicionar Activity ao Project");
			
			int id = Read_ID("ID da Activity");
			Activity ativ = null;
			boolean encontrado = false;
			
			for(Activity a : activities) {
				if(a.getId() == id) {
					encontrado = true;
					ativ = a;
					break;
				}
			}
			if(encontrado) {
				ativ.print("\n----------------------------------------------\n",
						"----------------------------------------------\n",true);

				System.out.println("\nConfirma inclus??o desta atividade ao projeto?");
				
				System.out.println("\n1 - Confirmar");
				System.out.println("2 - Inserir outra ID");
				System.out.println("3 - Cancelar e voltar");
				
				switch(Read_Option(new int[] {1,2,3})) {
				case 1:
					p.Adding_Activies(ativ);
					System.out.println("\nActivity inserida ao projeto com sucesso!");
					return;
				case 2:
					continue;
				case 3:
					System.out.println("\nOpera????o cancelada!");
					return;
				}
			} else {
				System.out.println("\nID de atividade n??o encontrada!");
				
				System.out.println("\nDeseja adicionar uma nova atividade ao sistema?");
				
				System.out.println("\n1 - Cadastrar nova atividade");
				System.out.println("2 - Inserir outra ID");
				System.out.println("3 - Cancelar e voltar");
				
				switch(Read_Option(new int[] {1,2,3})) {
				case 1:
					adicionarAtividade();
					continue;
				case 2:
					continue;
				case 3:
					System.out.println("\nOpera????o cancelada!");
					return;
				}
			}
		}
	}
	
	private static void Removing_Activies(Project p) {
		System.out.println("\nRemover Activity ao Project");

		while(true) {
			int id = Read_ID("ID");
			Activity ativ = null;
			boolean encontrado = false;

			for(Activity a : activities) {
				if(a.getId() == id) {
					encontrado = true;
					ativ = a;
					break;
				}
			}

			if(encontrado) {
				ativ.print("\n----------------------------------------------\n",
						"----------------------------------------------\n", true);
				System.out.println("\nConfirma exclus??o desta atividade do projeto?");
				
				System.out.println("\n1 - Confirmar exclus??o");
				System.out.println("2 - Inserir outro ID de atividade");
				System.out.println("3 - Cancelar e voltar");
				
				switch(Read_Option(new int[] {1,2,3})) {
				case 1:
					p.Removing_Activies(ativ);
					continue;
				case 2:
					continue;
				case 3:
					System.out.println("\nOpera????o cancelada!");
					return;
				}
			} else {
				System.out.println("ID de atividade n??o encontrado!");
				
				System.out.println("\n1 - Inserir outro ID de atividade");
				System.out.println("2 - Cancelar e voltar");
				
				switch(Read_Option(new int[] {1,2})) {
				case 1:
					continue;
				case 2:
					System.out.println("\nOpera????o cancelada!");
					return;
				}
			}
		}	
	}
	
	private static void List_Activies(Project p) {
		System.out.println("\nLista de Atividades do projeto");
		
		System.out.println("----------------------------------------------");
		for(Activity a : p.getActivies()) {
			a.print("","----------------------------------------------\n", false);
		}
		
		return;
	}

	private static void Modify_Status(Project p) {
		System.out.println("\nChange Project Status: ");
		System.out.println("Status atual: " + p.getStatusT());
		
		switch(p.getStatus()) {
		case Project.Status_Created:
			boolean podeAlterarStatus = true;

			if(p.getCoordinator() == null) {
				podeAlterarStatus = false;
				System.out.println("Coordenador do projeto n??o definido.");
			}
		
			if(p.getDescription() == null) {
				podeAlterarStatus = false;
				System.out.println("Descri????o do projeto n??o definida.");
			}
			
			if(p.getStart() == null) {
				podeAlterarStatus = false;
				System.out.println("Data de in??cio do projeto n??o definida.");
			}
			
			if(p.getEnd() == null) {
				podeAlterarStatus = false;
				System.out.println("Data de t??rmino do projeto n??o definida.");
			}
			
			if(p.getProjectUsers().size() == 0) {
				podeAlterarStatus = false;
				System.out.println("Project n??o possui profissionais associados.");
			}
		
			if(p.getActivies().size() == 0) {
				podeAlterarStatus = false;
				System.out.println("Project n??o possui activities associadas.");
			}
			
			if(podeAlterarStatus) {
				System.out.println("\nDeseja alterar o status do projeto para INICIADO?.");
				
				System.out.println("\n1 - Sim");
				System.out.println("2 - N??o");
				
				switch(Read_Option(new int[] {1,2})) {
				case 1:
					if(p.getStart().isBefore(LocalDateTime.now())) {
						p.setStatus(Project.Status_Started);
						
						System.out.println("\nStatus alterado com sucesso!");
						return;
					} else {
						System.out.println("\nImposs??vel alterar status! Data de in??cio anterior a data de hoje.");
					}
				case 2:
					System.out.println("\nOpera????o cancelada!");
					return;
				}
			} else {
				System.out.println("\nO status do projeto n??o pode ser alterado at?? que todas as pend??ncias sejam resolvidas.");
				return;
			}
		case Project.Status_Started:
			if(p.getStart().isBefore(LocalDateTime.now())) {
				p.setStatus(Project.Status_In_Progress);
				
				System.out.println("\nStatus alterado automaticamente para EM ANDAMENTO!");
				break;
			} else {
				System.out.println("\nAguarde a data de in??cio do projeto. O sistema ir?? alterar o status automaticamente.");
			}
			return;
		case Project.Status_In_Progress:
			System.out.println("\nDeseja alterar o status do projeto para CONCLU??DO?.");
			
			System.out.println("\n1 - Sim");
			System.out.println("2 - N??o");
			
			switch(Read_Option(new int[] {1,2})) {
			case 1:
				p.setStatus(Project.Status_Completed);
				System.out.println("\nStatus alterado com sucesso!");
				break;
			case 2:
				System.out.println("\nOpera????o cancelada!");
			}
			
			return;
		case Project.Status_Completed:
			System.out.println("\nO projeto j?? foi conclu??do. Imposs??vel alterar status.");
			return;
		}
		
	}
	private static void Users() {
		while(true) {
			System.out.println("\nUsuarios");
			System.out.println("(1) Cadastrar");
			System.out.println("(2) Remover");
			System.out.println("(3) Consultar");
			System.out.println("(4) Voltar");
							
			switch(Read_Option(new int[]{1,2,3,4})) {
			case 1:
				cadastrarUsuario();
				break;
			case 2:
				removerUsuario();
				break;
			case 3:
				consultarUsuarios();
				break;
			case 4:
				System.out.println("");
				return;
			}
		}
	}

	private static void cadastrarUsuario() {
		Users novoUser = null;
		String nome;
		int ra;

		System.out.println("\nCadastrar usu??rio ");

		ra = Read_ID("RA(N??mero de Matr??cula)");

		System.out.print("Nome: ");
		nome = Scan();

		System.out.println("Categoria:");
		System.out.println("(1) Aluno de Gradua????o");
		System.out.println("(2) Aluno de Mestrado");
		System.out.println("(3) Aluno de Doutorado");
		System.out.println("(4) Teacher");
		System.out.println("(5) Researcher");
		System.out.println("(6) Professional");
		System.out.println("(7) T??cnico");

		switch(Read_Option(new int[] {1,2,3,4,5,6,7})) {
		case Users.Graduate_Student:
			novoUser = new Graduate_Student(ra, nome);
			break;
		case Users.Mastering_Student:
			novoUser = new Mastering_Student(ra, nome);
			break;
		case Users.PHD_Studant:
			novoUser = new Phd_Studant(ra, nome);
			break;
		case Users.Teacher:
			novoUser = new Teacher(ra, nome);
			break;
		case Users.Researcher:
			novoUser = new Researcher(ra, nome);
			break;
		case Users.Professional:
			novoUser = new Professional(ra, nome);
			break;
		case Users.Technician:
			novoUser = new Technician(ra, nome);
			break;
		}
					
		System.out.println("\nAs informa????es estao corretas?");
		
		novoUser.print("\n----------------------------------------------\n",
				"----------------------------------------------\n\n");
		
		System.out.println("1 - Confirmar e cadastrastar ");
		System.out.println("2 - Cancelar e voltar");
		
		switch(Read_Option(new int[] {1,2})) {
		case 1:
			for(Users u : users) {
				if(u.getR() == ra) {
					System.out.println("\nRA j?? cadastrado! Opera????o cancelada!");
					return;
				}
			}
			users.add(novoUser);

			System.out.println("\nUsu??rio cadastrado com sucesso!");
		case 2:
			return;
		}
		
	}
	private static void removerUsuario() {
		if(users.size() == 0) {
			System.out.println("N??o h?? usu??rios para remover!\n");
			return;
		}	
		
		int ra;
		System.out.println("\nRemover usu??rio");

		ra = Read_ID("RA");
		
		int index = Users.IndexList(users, ra);

		if(index != -1) {
			System.out.println("\nDeseja remover o usu??rio?");
	
			users.get(index).print("\n----------------------------------------------\n",
					"----------------------------------------------\n\n");
			
			System.out.println("1 - Remover e voltar");
			System.out.println("2 - Cancelar e voltar");

			switch(Read_Option(new int[] {1,2})) {
			case 1:
				users.remove(index);
				System.out.println("\nUsu??rio removido com sucesso!");
				return;
			case 2:
				System.out.println("\nOpera????o cancelada!");
				return;
			}
		}
		System.out.println("\nUsu??rio n??o encontrado!");
		return;
			
	}
	private static void consultarUsuarios() {
		if(users.size() == 0) {
			System.out.println("N??o h?? usu??rios para consultar!\n");
			return;
		}

		System.out.println("\nConsultar usu??rio");
		
		System.out.println("1 - Lista de Usu??rios");
		System.out.println("2 - Buscar por RA");

		switch(Read_Option(new int[] {1,2})) {
		case 1:
			System.out.println("\nLista de Usu??rios");
			System.out.println("----------------------------------------------");
			
			for(Users u : users) {
				u.print("", "----------------------------------------------\n");
			}
			
			return;
		case 2:
			System.out.println("\nBuscar por RA");
			int ra = Read_ID("RA");
			
			for(Users u : users) {
				if(u.getR() == ra) {
					u.print("\n----------------------------------------------\n",
							"----------------------------------------------\n");
					return;
				}
			}

			System.out.println("\nUsu??rio n??o encontrado!");
			
			return;
		}
	}
		private static void Activies() {
		while(true) {
			System.out.println("\nAtividades ");
			System.out.println("1 - Adicionar");
			System.out.println("2 -  Remover");
			System.out.println("3 - Modificar");
			System.out.println("4 - Consultar");
			System.out.println("5 - Voltar");
							
			switch(Read_Option(new int[]{1,2,3,4,5})) {
			case 1:
				adicionarAtividade();
				break;
			case 2:
				removerAtividade();
				break;
			case 3:
				modificarAtividade();
				break;
			case 4:
				consultarAtividades();
				break;
			case 5:
				System.out.println("");
				return;
			}
		}	
	}
	
	
	private static void adicionarAtividade() {
		Users responsavel = null;
		String descricao;
		LocalDateTime inicio;
		LocalDateTime termino;
		int ra;
		boolean continuar = true;
		boolean encontrado;
		
		System.out.println("\nAdicionar Activity");
		
		while(continuar) {
			ra = Read_ID("RA do Respons??vel");
			
			encontrado = false;
			
			for(Users u : users) {
				if (u.getR() == ra) {
					encontrado = true;

					System.out.println("\nConfirma respons??vel pela atividade?");
					u.print("\n----------------------------------------------\n",
							"----------------------------------------------\n");
					
					System.out.println("\n1 - Sim");
					System.out.println("2 - N??o");
					System.out.println("3 - Cancelar e voltar");
					
					switch(Read_Option(new int[] {1,2,3})) {
					case 1:
						responsavel = u;
						continuar = false;
						break;
					case 2:
						System.out.println("");
						break;
					case 3:
						System.out.println("\nOpera????o cancelada!");
						return;
					}
					break;
				}
			}

			if(continuar && !encontrado) {
				System.out.println("\nUsu??rio n??o encontrado!\n");
				System.out.println("1 - Ler outro RA");
				System.out.println("2 - Cancelar e voltar");
				
				switch(Read_Option(new int[] {1,2})) {
				case 1:
					break;
				case 2:
					System.out.println("\nOpera????o cancelada!");
					return;
				}
			}
		}
		
		System.out.println("\nDescri????o da atividade: ");
		descricao = Scan();

		while(true) {
			System.out.println("Data de inicio:");
			inicio = Read_Date();
			
			System.out.println("Data de t??rmino:");
			termino = Read_Date();

			if(termino.isAfter(inicio)) {
				break;

			} else {
				System.out.println("\nIn??cio e t??rmino inv??lidos! A data de in??cio ?? posterior ?? data de t??rmino.\n");
				continue;
			}
		}
		Activity a = new Activity(activie_id++, descricao, inicio, termino, responsavel);

		System.out.println("\nOs dados da atividade est??o corretos?");
		
		a.print("\n----------------------------------------------\n",
				"----------------------------------------------\n", false);
		
		System.out.println("\n1 - Confirmar e adicionar");
		System.out.println("2 - Cancelar e voltar");

		switch(Read_Option(new int[] {1,2})) {
		case 1:
			activities.add(a);
			System.out.println("\nActivity adicionada com sucesso!\n");
			
			break;
		case 2:
			System.out.println("\nOpera????o cancelada!");
			return;
		}
		while(true) {
			System.out.println("Deseja adicionar profissionais envolvidos na atividade?");
			System.out.println("\n1 - Sim");
			System.out.println("2 - Voltar");

			switch(Read_Option(new int[] {1,2})) {
			case 1:
				adicionarProfissionais(a);
				
				a.print("\n----------------------------------------------\n",
						"----------------------------------------------\n", false);
				
				break;
			case 2:
				System.out.println("\nOpera????o terminada!");
				return;
			}	
		}
	}
	private static void removerAtividade() {
		if(activities.size() == 0) {
			System.out.println("N??o h?? activities para remover!\n");
			return;
		}		
		
		int id;		
		boolean encontrado;
		System.out.println("\nRemover Activity");
		Activity ativ = null;

		System.out.println("Atividades cadastradas:");
		System.out.println("----------------------------------------------");
		for(Activity a : activities) {
			a.print("", "----------------------------------------------\n", true);
		}
		
		while(true) {
			id = Read_ID("ID da Activity");

			encontrado = false;

			for(Activity a : activities) {
				if (a.getId() == id) {
					encontrado = true;
					ativ = a;
					break;
				}
			}

			if(encontrado) {
				System.out.println("\nDeseja remover a seguinte atividade?");

				ativ.print("\n----------------------------------------------\n", 
						"----------------------------------------------\n", true);
				
				
				System.out.println("\n1 - Confirmar e remover");
				System.out.println("2 - Cancelar e voltar");
				
				switch(Read_Option(new int[] {1,2})) {
				case 1:
					activities.remove(ativ);
					System.out.println("\nActivity removida com sucesso!");
					return;
				case 2:
					System.out.println("\nOpera????o cancelada!");
					return;
				}
			} else {
				System.out.println("\nActivity n??o encontrada!");
				continue;
			}	
		}
	}

	private static void modificarAtividade() {
		if(activities.size() == 0) {
			System.out.println("N??o h?? activities para modificar!\n");
			return;
		}
		
		int id;		
		boolean encontrado;
		System.out.println("\nModificar Activity");
		Activity ativ = null;
		
		System.out.println("Atividades cadastradas:");
		System.out.println("----------------------------------------------");
		for(Activity a : activities) {
			a.print("", "----------------------------------------------\n", true);
		}
		
		while(true) {
			id = Read_ID("ID da Activity");

			encontrado = false;

			for(Activity a : activities) {
				if (a.getId() == id) {
					encontrado = true;
					ativ = a;
					break;
				}
			}

			if(encontrado) {
				System.out.println("\nDeseja modificar a seguinte atividade?");

				ativ.print("\n----------------------------------------------\n", 
						"----------------------------------------------\n", true);
				
				
				System.out.println("\n1 - Confirmar e modificar");
				System.out.println("2 - Cancelar e voltar");
				
				switch(Read_Option(new int[] {1,2})) {
				case 1:
					break;
				case 2:
					System.out.println("\nOpera????o cancelada!");
					return;
				}
			} else {
				System.out.println("\nActivity n??o encontrada!");
				continue;
			}
			
			while(true) {
				System.out.println("\nO que voc?? deseja modificar?");
				System.out.println("1 - Respons??vel");
				System.out.println("2 -  Descri????o");
				System.out.println("3 - In??cio");
				System.out.println("4 - T??rmino");
				System.out.println("5 - Incluir usu??rios envolvidos");
				System.out.println("6 - Excluir usu??rios envolvidos");
				System.out.println("7 - Alterar tarefa de users");
				System.out.println("8 - Voltar");
				
				switch(Read_Option(new int[] {1,2,3,4,5,6,7,8})) {
				case 1:
					modificarResponsavel(ativ);					
					continue;
				case 2:
					modificarDescricaoAtividade(ativ);
					continue;
				case 3:
					modificarInicioAtividade(ativ);
					continue;
				case 4:
					modificarTerminoAtividade(ativ);
					continue;
				case 5:
					adicionarProfissionais(ativ);					
					continue;
				case 6:
					removerProfissional(ativ);					
					continue;
				case 7:
					modificarTarefa(ativ);
					continue;
				case 8:
					return;
				}
			}			
		}
	}

	private static void consultarAtividades() {
		if(activities.size() == 0) {
			System.out.println("N??o h?? activities para consultar!\n");
			return;
		}
		System.out.println("\nConsultar activities");
		
		System.out.println("1 -  Lista de Atividades");
		System.out.println("2 - Buscar por ID");

		switch(Read_Option(new int[] {1,2})) {
		case 1:
			System.out.println("\nLista de Atividades");
			System.out.println("----------------------------------------------");
			
			for(Activity a : activities) {
				a.print("", "----------------------------------------------\n", false);
			}
			
			return;
		case 2:
			System.out.println("\nBuscar por ID");

			int id = Read_ID("ID");

			for(Activity a : activities) {

				if(a.getId() == id) {
					a.print("\n----------------------------------------------\n",
							"----------------------------------------------\n", false);
					return;
				}
				
				
			}
			System.out.println("\nActivity n??o encontrada!");
			
			return;
		}
	}

	private static void modificarResponsavel(Activity ativ) {

		
		System.out.println("\n== Modificar Respons??vel da Activity ==");
		
		int ra;
		boolean encontrado;

		ra = Read_ID("RA do Novo Respons??vel");
		if(ativ.getCoordinator().getR() == ra) {
			System.out.println("O RA do Novo Respons??vel ?? id??ntico ao RA do Antigo Respons??vel!");
			System.out.println("\nOpera????o cancelada!");
			return;
		}
		
		encontrado = false;
		for(Users u : users) {
			if(u.getR() == ra) {
				encontrado = true;

				System.out.print("\nVoc?? deseja substituir o respons??vel?");
				
				ativ.getCoordinator().print("\n----------------------------------------------\nAntigo Respons??vel:\n",
						"----------------------------------------------\n");
				
				u.print("\n----------------------------------------------\nNovo Respons??vel:\n",
						"----------------------------------------------\n");
				
				System.out.println("1 -  Confirmar e substituir");
				System.out.println("2 - Cancelar e voltar");
				
				switch(Read_Option(new int[] {1,2})) {
				case 1:
					ativ.setCoordinator(u);
					System.out.println("\nRespons??vel alterado com sucesso!");
					return;
				case 2:
					System.out.println("\nOpera????o cancelada!");
					return;
				}
			}			
		}
		
		if(!encontrado){
			System.out.println("\nRA n??o encontrado! Opera????o cancelada!");
		}		
	}

	private static void modificarDescricaoAtividade(Activity ativ) {

		
		System.out.println("\nModificar Descri????o da Activity");
		
		String descricao;
		
		System.out.println("Nova descri????o: ");
		descricao = Scan();
		
		System.out.println("\nDeseja substituir a descri????o?");
		
		System.out.println("\nDescri????o antiga: " + ativ.getDescription());
		
		System.out.println("Nova descri????o: " + descricao);
		
		System.out.println("\n1 - Confirmar e substituir");
		System.out.println("2 - Cancelar e voltar");
		
		switch(Read_Option(new int[] {1,2})) {
		case 1:
			ativ.setDescription(descricao);
			System.out.println("\nDescri????o alterada com sucesso!");
			return;
		case 2:
			System.out.println("\nOpera????o cancelada!");
			return;
		}
	}
	
	private static void modificarInicioAtividade(Activity ativ) {

		System.out.println("\nModificar Data de In??cio da Activity");
		
		LocalDateTime inicio;
		
		System.out.println("Nova data de inicio:");
		inicio = Read_Date();
		
		System.out.println("\nDeseja substituir a data de in??cio?");
		
		System.out.println("\nData de in??cio antigo: " + ativ.getStartDate().toString());
		
		System.out.println("Nova data de in??cio: " + inicio.toString());
		
		System.out.println("\n1 - Confirmar e substituir");
		System.out.println("2 - Cancelar e voltar");
		
		switch(Read_Option(new int[] {1,2})) {
		case 1:
			ativ.setStartDate(inicio);
			System.out.println("\nData de in??cio alterada com sucesso!");
			return;
		case 2:
			System.out.println("\nOpera????o cancelada!");
			return;
		}
	}
	
	private static void modificarTerminoAtividade(Activity ativ) {

		
		System.out.println("\nModificar Data de T??rmino da Activity");
		
		LocalDateTime termino;
		
		System.out.println("Nova data de inicio:");
		termino = Read_Date();
		
		System.out.println("\nDeseja substituir a data de t??rmino?");
		
		System.out.println("\nData de t??rmino antigo: " + ativ.getEndDate().toString());
		
		System.out.println("Nova data de t??rmino: " + termino.toString());
		
		System.out.println("\n1 - Confirmar e substituir");
		System.out.println("2 - Cancelar e voltar");
		
		switch(Read_Option(new int[] {1,2})) {
		case 1:
			ativ.setEndDate(termino);
			System.out.println("\nData de t??rmino alterada com sucesso!");
			return;
		case 2:
			System.out.println("\nOpera????o cancelada!");
			return;
		}
	}

	private static void adicionarProfissionais(Activity a) {
		System.out.println("\n== Adicionar Profissionais ?? Activity ==");
		int ra;
		String tarefa;
		boolean encontrado;
		Users user = null;
		
		while(true) {
			ra = Read_ID("RA");
			
			encontrado = false;

			for(Users u : users) {
				if (u.getR() == ra) {
					encontrado = true;
					user = u;
					break;
				}
			}

			if(encontrado) {
				if(a.getProjectUsers().contains(user)) {
					System.out.println("\nUsu??rio j?? consta na lista de profissionais associados a esta atividade!");					
					continue;
				} else {
					user.print("\n----------------------------------------------\n", 
							"----------------------------------------------\n");					
					
					System.out.println("\nTarefa: ");
					tarefa = Scan();
					
					System.out.println("\nDeseja incluir o seginte profissional e tarefa?");
					
					System.out.println("\n----------------------------------------------");
					System.out.println("Nome: " + user.getName());
					System.out.println("Tarefa: " + tarefa);
					System.out.println("----------------------------------------------\n");
					
					System.out.println("1 - Confirmar e incluir");
					System.out.println("2 - Cancelar e voltar");
					
					switch(Read_Option(new int[] {1,2})) {
					case 1:
						a.Add_Collaborator(user, tarefa);
						System.out.println("\nProfessional inclu??do com sucesso!");
						return;
					case 2:
						System.out.println("\nOpera????o cancelada!");
						return;
					}
				}
			} else {
				System.out.println("\nUsu??rio n??o encontrado!");					
				continue;
			}
		}
	}
	private static void removerProfissional(Activity ativ) {
		System.out.println("\nRemover Profissionais da Activity");
		int ra, index = -1;
		boolean encontrado;
		
		System.out.println("");
		ativ.printProfessionals();
		
		while(true) {
			ra = Read_ID("RA");
			
			// Sup??e que n??o encontramos o usu??rio
			encontrado = false;

			for(int i = 0; i < ativ.getProjectUsers().size(); i++) {
				if (ativ.getProjectUsers().get(i).getR() == ra) {
					encontrado = true;
					index = i;
					break;
				}
			}
			if(encontrado) {
				System.out.println("\nDeseja remover o seginte profissional e tarefa?");
				
				System.out.println("\n----------------------------------------------");
				System.out.println("Nome: " + ativ.getProjectUsers().get(index).getName());
				System.out.println("Tarefa: " + ativ.getActivies().get(index));
				System.out.println("----------------------------------------------\n");
				
				System.out.println("1 - Confirmar e remover");
				System.out.println("2 - Cancelar e voltar");
				
				switch(Read_Option(new int[] {1,2})) {
				case 1:
					ativ.Removing_Collaborator(index);
					System.out.println("\nProfessional removido com sucesso!");
					return;
				case 2:
					System.out.println("\nOpera????o cancelada!");
					return;
				}
			} else {
				System.out.println("\nUsu??rio n??o encontrado!");					
				continue;
			}
		}
	}
	private static void modificarTarefa(Activity ativ) {
		System.out.println("\nModificar Tarefa de Professional da Activity");
		int ra, index;

		System.out.println("");
		ativ.printProfessionals();
		
		while(true) {
			ra = Read_ID("RA");
			
			index = Users.IndexList(ativ.getProjectUsers(), ra);

			if(index != -1) {

				System.out.println("\nDeseja modificar a tarefa do seguinte profissional?");
				
				System.out.println("\n----------------------------------------------");
				System.out.println("Nome: " + ativ.getProjectUsers().get(index).getName());
				System.out.println("Tarefa: " + ativ.getActivies().get(index));
				System.out.println("----------------------------------------------\n");
				
				System.out.println("1 - Confirmar e modificar");
				System.out.println("2 - Cancelar e voltar");
				
				switch(Read_Option(new int[] {1,2})) {
				case 1:
					substituirTarefa(ativ, index);
					return;
				case 2:
					System.out.println("\nOpera????o cancelada!");
					return;
				}
			} else {
				System.out.println("\nUsu??rio n??o encontrado!");					
				continue;
			}
		}
	}
	private static void substituirTarefa(Activity ativ, int index) {
		System.out.println("\nSubstituir Tarefa");
		
		String tarefa;
		
		System.out.println("Nova tarefa: ");
		tarefa = Scan();
		
		System.out.println("\nDeseja substituir a tarefa?");
		
		System.out.println("\n----------------------------------------------");
		System.out.println("Tarefa antiga: " + ativ.getActivies().get(index));
		System.out.println("Nova tarefa: " + tarefa);
		System.out.println("----------------------------------------------\n");
		
		System.out.println("1 -Confirmar e substituir");
		System.out.println("2 - Cancelar e voltar");
		
		switch(Read_Option(new int[] {1,2})) {
		case 1:
			ativ.getActivies().set(index, tarefa);
			return;
		case 2:
			System.out.println("\nOpera????o cancelada!");
			return;
		}
	
	}
	private static void Reports() {
		while(true) {
			System.out.println("\nRelat??rios");
			System.out.println("1 - Projetos");
			System.out.println("2 -  Atividades");
			System.out.println("3 - Voltar");
							
			switch(Read_Option(new int[]{1,2,3})) {
			case 1:
				relatorioDeProjetos();
				break;
			case 2:
				relatorioDeAtividades();
				break;
			case 3:
				System.out.println("");
				return;
			}
		}
	}
	private static void relatorioDeProjetos() {
		if(projects.size() == 0) {
			System.out.println("N??o h?? projects para exibir relat??rios!\n");
			return;
		}
		
		System.out.println("\nRelat??rio de activities");
		
		System.out.println("----------------------------------------------");
		for(Project p : projects) {
			p.Print_Activies("", "----------------------------------------------", false);
		}
		
		return;
	}
	private static void relatorioDeAtividades() {
		if(activities.size() == 0) {
			System.out.println("N??o h?? activities para exibir relat??rios!\n");
			return;
		}
		
		System.out.println("\nRelat??rio de activities");
		
		System.out.println("----------------------------------------------");
		for(Activity a : activities) {
			a.print("", "----------------------------------------------", false);
		}
		
		return;
	}

	public static void main(String [] args) {
		while(true) {
			switch(Menu()) {
			case 1:
				Projects_Menu();
				break;
			case 2:
				Users();
				break;
			case 3:
				Activies();
				break;
			case 4:
				Reports();
				break;
			case 5:
				return;	
			}
		}
	}
}

