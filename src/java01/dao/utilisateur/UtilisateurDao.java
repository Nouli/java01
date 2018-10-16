package java01.dao.utilisateur;


import java01.dao.entity.EntityDao;
import java01.entity.Utilisateur;

public class UtilisateurDao extends EntityDao  {
	 
	public UtilisateurDao() {

	}
	public UtilisateurDao(Class entityClass) {
		super(Utilisateur.class);
	}



/*
	public ArrayList<Utilisateur> findAll() throws  AppDataAccessException, UserNotFoundException {
		ArrayList utilisateurs = new ArrayList();
	try{
			String query = "SELECT id ,firstName ,lastname ,gender ,age FROM projet.utilisateur order by id";
			connectDB.executeQuery(query);
			while ( connectDB.next() ) {
				
				Gender gender = Enum.valueOf(Gender.class, connectDB.getString("gender"));
				Utilisateur user= new Utilisateur (connectDB.getRs().getLong("id"),connectDB.getRs().getString("firstName"),connectDB.getRs().getString("lastName"),gender,connectDB.getRs().getInt("age"));
				utilisateurs.add(user);
							}
	}catch(Exception e){
	throw new AppDataAccessException(); 
		}
		return utilisateurs;
	}
*/
}
	
