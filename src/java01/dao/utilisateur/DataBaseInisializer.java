package java01.dao.utilisateur;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.commons.lang3.EnumUtils;

import java01.dao.utilisateur.UtilisateurDao;
import java01.entity.Gender;
import java01.entity.Utilisateur;
public class DataBaseInisializer {
	public UtilisateurDao utilisateurDao;
	public DataBaseInisializer() {
		
	}
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	public  void initDataBase() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	UtilisateurDao utilisateurDao = new UtilisateurDao();
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DataBaseInisializer	initDataBase = new DataBaseInisializer() ;
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		int answer = 0;
		
		String menu = "Menu : 1.Liste des utilisateurs  2.Ajouter  3.Modifier  4.Supprimer 5.Quitter";
		System.out.println("-------------------------------------");
		System.out.println("Systeme de gestions des utilisateurs");
		System.out.println("-------------------------------------");
		System.out.println(menu);
		System.out.println(" Ce systéme vous permet de gérer les utilisateurs ");
		
		while ( answer != 5) {
			System.out.println(" Ce systéme vous permet de gérer les utilisateurs ");
			System.out.println(" taper le chiffre correspendant à la commande que vous souhaitez executer ");
			Scanner sc = new Scanner(System.in);
			answer = sc.nextInt();	
			switch (answer)
			{
			  case 1:
				  System.out.println("Lite des utilisateurs");
				    ArrayList<Utilisateur> utilisateurs = utilisateurDao.findAll();
					Iterator<Utilisateur> iterator = utilisateurs.iterator();
					while (iterator.hasNext()) {
						System.out.println(iterator.next());
					}
					System.out.println(menu);
			    break;
			  case 2:
			    System.out.println("pour Ajouter un utilisateur veuillez insérer le nom, prénom , gendre et age");
			    System.out.println("-------------------------------------------------------");
			    System.out.println("Start : ");
			    System.out.println("--------------------");
			    String nul = sc.nextLine();
			    System.out.println("Insert FirstName : ");
			    System.out.println("--------------------");
			    String firstName = sc.nextLine();
			    System.out.println("Insert LastName :");
			    System.out.println("--------------------");
			    String lastName = sc.nextLine();
			    System.out.println("Insert Gender ( male, female , other : )");
			    System.out.println("--------------------");
			    String var = sc.nextLine();
			    while(EnumUtils.isValidEnum(Gender.class, var) != true ){
			    	 System.out.println("Insert Gender ( male, female , other : )");
					    System.out.println("--------------------");
					     var = sc.nextLine();		
			    }
			    Gender gender = Enum.valueOf(Gender.class, var);
			    System.out.println("Insert Age :");
			    System.out.println("--------------------");
			    String ageString = sc.nextLine();
			    while(isInteger(ageString)==false) {
			    	System.out.println("pensez à utiliser des chiffres");
			    	System.out.println("Insert Age :");
			    	System.out.println("--------------------");
			    	ageString = sc.nextLine();			    	
			    }
			    int age = Integer.parseInt(ageString);
			    Utilisateur user = new Utilisateur(firstName,lastName,gender,age);
			    
			    utilisateurDao.add(user);
			    System.out.println(menu);
			    break;
			  case 3:
				   	System.out.println("Inserer l'Id correspendante à l'utilisateur que vous souhaiter modifier ;(pour annuler taper 0) :");
			    	System.out.println("----------------------------------------------------------------------------------------------");
			    System.out.println(menu);
			    break;
			  case 4:
				    
				    String chiffre = sc.nextLine();
				    while(isInteger(chiffre)==false) {
				    	System.out.println("pensez à taper des chiffres");
				    	System.out.println("Inserer l'Id correspendante à l'utilisateur que vous souhaiter supprimer ;(pour annuler taper 0) :");
				    	System.out.println("----------------------------------------------------------------------------------------------");
				    	chiffre = sc.nextLine();
				    }
				    int id = Integer.parseInt(chiffre);
				    	switch (id)
						{
						case 0:
							System.out.println(menu);
						break;
						default:
							String reponse = "N";
							while(reponse.equals("Y")==false && reponse.equals("y")==false) {
								System.out.println("Êtes-vous sûr de vouloir supprimer cet utilisateur ?(Y/N) '");
								System.out.println(utilisateurDao.find(id).toString());
								reponse = sc.nextLine();
								System.out.println(reponse);
							}
							utilisateurDao.delete(id);
							System.out.println(menu);
						}
				 
			    break;
			  case 5:
				    System.out.println("Déconnexion");
			    break;
			  default:
			    System.out.println("Veuillez tapez un chiffre qui correspond à une commande.");
			}
			
		}
	}

}


