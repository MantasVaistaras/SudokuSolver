
public class ValueOutsideTheValidInterval extends Exception {
	
	public ValueOutsideTheValidInterval(char symbol) {
		super("The value " + symbol + " is too large according tothe size of the board");
	}

}
