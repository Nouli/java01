package java01.dao.utilisateur;


import java01.dao.entity.EntityDao;
import java01.entity.Utilisateur;

public class UtilisateurDao extends EntityDao  {
	 
	public UtilisateurDao() {

	}
	public UtilisateurDao(Class<?> entityClass) {
		super(Utilisateur.class);
	}

}
	
