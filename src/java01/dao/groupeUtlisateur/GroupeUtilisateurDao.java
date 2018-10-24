package java01.dao.groupeUtlisateur;



import java01.dao.entity.EntityDao;
import java01.entity.GroupeUtilisateur;
import java01.entity.Utilisateur;


public class GroupeUtilisateurDao extends EntityDao{


	public GroupeUtilisateurDao() {
		super(GroupeUtilisateur.class);
	}
	public GroupeUtilisateurDao(Class<?> entityClass) {
		super(GroupeUtilisateur.class);
	}
	public void addUser(Utilisateur user) {
		//this.utilisateurs.add(user);
	}
	public void deleteUser(Utilisateur user) {
		//this.utilisateurs.remove(user);
	}
}
	