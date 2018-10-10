package Exception;

public class UserNotFoundException extends Exception{
 public UserNotFoundException() {
	 super("Not found");
 }
 public UserNotFoundException(String message) {
	 super (message);
 }
}
