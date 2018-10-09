package java01.entity;

public enum Gender {
	male("male"),
	female("female"),
	other("other");
	
 private final String gender;
 
 private Gender(String s) {
	 gender = s ;
 }
  public boolean equalsGender(String gender2) {
	  return gender.equals(gender2);
  }
  public String toString() {
	  return this.gender;
  }

}
