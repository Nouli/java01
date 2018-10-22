package java01.entity;

import java.util.ArrayList;
import java.util.List;


public class GroupeUtilisateur extends Entity{
	private Role name;
	private List<Utilisateur> utilisateurs  = new ArrayList<Utilisateur>();
	
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
	
	public GroupeUtilisateur(Long id, Role name, List<Utilisateur> utilisateurs) {
		super();
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
	public void addUser(Utilisateur user) {
		this.utilisateurs.add(user);
	}
	public void deleteUser(Utilisateur user) {
		this.utilisateurs.remove(user);
	}
	@Override
	public String toString() {
		return "GroupeUtilisateur [name=" + name + ", utilisateurs=" + utilisateurs.toString() + "]";
	}
	
}
