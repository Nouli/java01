package java01.entity;

import java.util.List;


public class GroupeUtilisateur {
	private long id;
	private Role name;
	private List<Utilisateur> utilisateurs;
	public Role getName() {
		return name;
	}
	public void setName(Role name) {
		this.name = name;
	}
	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}
	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	public GroupeUtilisateur(long id, Role name, List<Utilisateur> utilisateurs) {
		super();
		this.id = id;
		this.name = name;
		this.utilisateurs = utilisateurs;
	}
	public GroupeUtilisateur(Role name, List<Utilisateur> utilisateurs) {
		super();
		this.name = name;
		this.utilisateurs = utilisateurs;
	}
	public GroupeUtilisateur() {
		super();
	}
	public GroupeUtilisateur(Role name) {
		super();
		this.name = name;
	}
	
}
