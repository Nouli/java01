package Exception;

public class AppDataAccessException extends Exception{
	 public AppDataAccessException() {
		 super("Cannot Access Data");
	 }
	 public AppDataAccessException(String message) {
		 super (message);
	 }
}
