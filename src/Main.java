
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import org.apache.commons.lang3.EnumUtils;
import Exception.AppDataAccessException;
import Exception.UserNotFoundException;
import java01.dao.utilisateur.UtilisateurDao;
import java01.entity.Gender;
import java01.entity.Utilisateur;

public class Main {
	static String menu = "Menu : 1.Liste des utilisateurs  2.Ajouter  3.Modifier  4.Supprimer 5.Quitter";
	private static Scanner sc;
	private static Scanner sc2;
	private static Scanner sc3;
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}

	private static Utilisateur insertUtilisateur() {
			sc3 = new Scanner(System.in);
		 	System.out.println("Insert FirstName : ");
		    System.out.println("--------------------");
		    String firstName = sc3.nextLine();
		    System.out.println("Insert LastName :");
		    System.out.println("--------------------");
		    String lastName = sc3.nextLine();
		    System.out.println("Insert Gender ( male, female , other : )");
		    System.out.println("--------------------");
		    String var = sc3.nextLine();
		    while(EnumUtils.isValidEnum(Gender.class, var) != true ){
		    	 System.out.println("Insert Gender ( male, female , other : )");
				    System.out.println("--------------------");
				     var = sc3.nextLine();		
		    }
		    Gender gender = Enum.valueOf(Gender.class, var);
		    System.out.println("Insert Age :");
		    System.out.println("--------------------");
		    String ageString = sc3.nextLine();
		    while(isInteger(ageString)==false) {
		    	System.out.println("pensez à utiliser des chiffres");
		    	System.out.println("Insert Age :");
		    	System.out.println("--------------------");
		    	ageString = sc3.nextLine();			    	
		    }
		    int age = Integer.parseInt(ageString);
		    Utilisateur user = new Utilisateur(firstName,lastName,gender,age);
		    return user;
	}
	
	public static Long IdInteger(String valeur) {
		  while(isInteger(valeur)==false) {
			    sc = new Scanner(System.in);
		    	System.out.println("pensez à taper des chiffres");
		    	System.out.println("Inserer l'Id correspendante à l'utilisateur que vous souhaiter supprimer ;(pour annuler taper 0) :");
		    	System.out.println("----------------------------------------------------------------------------------------------");
		    	valeur = sc.nextLine();
		    }
		    return (long) Integer.parseInt(valeur);
	}
	
	public static void main(String[] args) throws UserNotFoundException, AppDataAccessException {
		UtilisateurDao utilisateurDao = new UtilisateurDao(Utilisateur.class);
		String answer = "";
		Long id ;
		String valeur;
		
		
		System.out.println("-------------------------------------");
		System.out.println("Systeme de gestions des utilisateurs");
		System.out.println("-------------------------------------");
		
		System.out.println(menu);
		
		while (!"5".equals(answer)) {
			System.out.println(" taper le chiffre correspendant à la commande que vous souhaitez executer ");
			sc = new Scanner(System.in);
			answer = sc.nextLine();	
			switch (answer)
			{
			  case "1":
				  System.out.println("Lite des utilisateurs");
				   ArrayList<Utilisateur> utilisateurs = utilisateurDao.findAll();
					Iterator<Utilisateur> iterator = utilisateurs.iterator();
					while (iterator.hasNext()) {
						System.out.println(iterator.next());
					}
					System.out.println(menu);
			    break;
			  case "2":		
				  
			    utilisateurDao.save(insertUtilisateur());
			    System.out.println(menu);
			    break;
			  case "3":
				  System.out.println("Inserer ID de l'utilisateur que vous souhaitez modifier");
			    		valeur = sc.nextLine();
					    id = IdInteger(valeur);
				try {
					utilisateurDao.select(id);
					utilisateurDao.update(insertUtilisateur(),id);	
				}catch(UserNotFoundException  e) {
					System.out.println("l'utilisateur que vous suhaitez modifier n'éxiste pas");
				}
			    
			    System.out.println(menu);
			    break;
			  case "4":	
				  	System.out.println("Inserer ID de l'utilisateur que vous souhaitez supprimer");
				    valeur = sc.nextLine();
				    
				    id = IdInteger(valeur);
				    try {
						utilisateurDao.select(id);

							String reponse = "N";
							while(reponse.equals("Y")==false && reponse.equals("y")==false) {
								System.out.println("Êtes-vous sûr de vouloir supprimer cet utilisateur ?(Y/N) '");
								System.out.println(utilisateurDao.select(id).toString());
								reponse = sc.nextLine();
								System.out.println(reponse);						
						
							utilisateurDao.delete(id);
							}
						 }catch(UserNotFoundException e){
							 System.out.println("l'Utilisateur choisis n'existe pas");
						 }catch(AppDataAccessException e) {
							 System.out.println("Cannot Access Data");
						 }
				    System.out.println(menu);    
			    break;
			  case "5":
				    System.out.println("Déconnexion");
			    break;
			  default:
			    System.out.println("Veuillez tapez un chiffre qui correspond à une commande.");
			}
			
		}
	}
}
