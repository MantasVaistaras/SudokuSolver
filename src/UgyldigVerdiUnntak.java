
public class UgyldigVerdiUnntak extends Exception {
	
	public UgyldigVerdiUnntak(char symbol) {
		super("The symbol " + symbol + " is not valid. Please check the supported characters!");
	}
	
	public UgyldigVerdiUnntak(int value) {
		super("The value " + value + " is not valid. Please check the supported characters!");
	}

}
