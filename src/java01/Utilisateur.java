package java01;

public class Utilisateur {
	
	private long id;
	private String firstName;
	private String lastName;
	private Gender gender;
	private int age;
	
	
	public Utilisateur() {

	}

	public Utilisateur(String firstName, String lastName, Gender gender, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		System.out.println(this.toString());
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender.toString();
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Utilisateur [firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", age=" + age
				+ "]";
	}
	
	
}
