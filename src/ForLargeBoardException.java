
public class ForLargeBoardException extends Exception {
	
	public ForLargeBoardException() {
		
		super("The given board is too large. Maximum supported size of the board is 64x64");
	}

}
