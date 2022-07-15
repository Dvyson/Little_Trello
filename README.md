# Little_Trello

## Sistema de Gerenciamento de Projetos

## Especificaçõe sobre o projeto:

## Foram utilizados no projetos os seguintes conceitos:

# Classe Abstrata:
        Classe Users 

# Herança: 
        A heraça acontece entre as classes: Graduate_Student, Mastering_Student, Phd_Studant, Teacher, Technician, Professional e Researcher. 
        Todas as classes citadas acima herdam da classe Users.

# Interface: 
        Os conceitos de Interface estão sendo utilizados na Interface, que está sendo implementada nas classes Users e na classe Trello.
        
# Exceptions:
        Foram utilizados os conceitos de Exceptions nas seguintes funções:
        Classe Trello: Metodo Read_Option() --> NumberFormatException
        Classe Trello: Metodo Read_Date() --> DateTimeException
        Classe Trello: Metodo Scan() --> IOException
        
# Padrões De Projeto:
        Foram identificados a necessidade de utilizar os seguintes padrões de projeto nas seguintes classes:
        Extract Method: Classe Trello: Activity_Reports(), Replace_Task(), Removing_Professional.
        Move Accumulation to Collecting Parameter: Classe Activity: ProfessionalPrint()
        Move Accumulation to Collecting Parameter: Classe Technician: PrintTechnical()
        Move Accumulation to Collecting Parameter: Classe Projects: Print_Activies()
