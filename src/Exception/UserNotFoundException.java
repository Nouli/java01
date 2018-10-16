package Exception;

public class UserNotFoundException extends Exception{
 public UserNotFoundException() {
	 super("404 : User not found");
 }
 public UserNotFoundException(String message) {
	 super (message);
 }
}
