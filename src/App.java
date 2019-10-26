import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class App {

	public static void main(String[] args) {
		
		Board board = null;
		
		try {
			board = readFile(args[0]);
		} catch (TooManyCharacterException e) {
			e.printStackTrace();
		} catch (ForLargeBoardException e) {
			e.printStackTrace();
		}	
		
		board.printBoard();		
		board.createDataStructure();
		
		//Find the solutions by using the "Brute Force" method
		board.createSudokuContainer();
		board.getRegion(0).fillThisRegionAndTheRest();	
		
		board.getSudokuContainer().printSolutions();
	}
	
	/*
	 *Method which takes the name of the file as the parameter, reads the file and returns the Board object
	 */
	
	public static Board readFile(String fileName) throws TooManyCharacterException, ForLargeBoardException {
		
		FileReader fr = null;
		
		try {
			fr = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			System.out.println("The file with a given name(path) which you provided does not exist");
			System.exit(0);
		}
		
		char rowsInTheBoxChar = 0;
		char columnsInTheBoxChar = 0;
		try {
			rowsInTheBoxChar = (char) fr.read();
			fr.skip(2);
			columnsInTheBoxChar = (char) fr.read();
			fr.skip(2);
		} catch (IOException e1) {
			System.out.println("Some character(s) in the file are not valid. Check the supported file format");
			System.exit(0);
		}
		
		int rowsInBox = Character.getNumericValue(rowsInTheBoxChar);
		int columnsInBox = Character.getNumericValue(columnsInTheBoxChar);
		
		if(rowsInBox > 64 || columnsInBox > 64) {
			throw new ForLargeBoardException();
		}
		
		Board board = new Board(rowsInBox, columnsInBox);
		int boardSize = board.getSizeOfTheBox()*board.getSizeOfTheBox();
		
		int i;
		int j = 0;

	    try {
			while ((i=fr.read()) != -1) {
				if(i == 13 || i == 10) {
					continue;
				}else {
					if(j == boardSize){
						throw new TooManyCharacterException();
					}
					board.addRegion(new Region((char) i, board, j), j);
					j++;					
				}
			}						
		} catch (IOException e) {
			System.out.println("Some character(s) in the file are not valid. Check the supported file format");
			System.exit(0);
		} catch (ValueOutsideTheValidInterval e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	  			
		return board;
	}
	
	public static int[] removeZeros(int[] values) {
		//removes zeros from the array
		int targetIndex = 0;
		for( int sourceIndex = 0;  sourceIndex < values.length;  sourceIndex++ )
		{
		    if( values[sourceIndex] != 0 )
		        values[targetIndex++] = values[sourceIndex];
		}
		int[] newArray = new int[targetIndex];
		System.arraycopy( values, 0, newArray, 0, targetIndex );
		return newArray;

	}
	
	/**
     * Converts (char) to the numeric value of type (int)
     *
     * @param symbol      symbol which will be converted
     * @return          numeric value of the char
     * @throws UgyldigVerdiUnntak 
     */
    public static int symbolToNumeric(char symbol) throws UgyldigVerdiUnntak {
        if (symbol == '.') {                			// empty
            return 0;
        } else if ('1' <= symbol && symbol <= '9') {    // symbol is in [1, 9]
            return symbol - '0';
        } else if ('A' <= symbol && symbol <= 'Z') {    // symbol is in [A, Z]
            return symbol - 'A' + 10;
        } else if (symbol == '@') {                   // symbol is @
            return 36;
        } else if (symbol == '#') {                   // symbol is #
            return 37;
        } else if (symbol == '&') {                   // symbol is &
            return 38;
        } else if ('a' <= symbol && symbol <= 'z') {    //symbol is in [a, z]
            return symbol - 'a' + 39;
        } else {                                    // symbol is not valid
            throw new UgyldigVerdiUnntak(symbol);
        }
    }

    /**
     * Converts numeric value (int) to the symbol (char)
     *
     * @param value     value converted
     * @param empty     symbol representing the value 0 (empty region)
     * @return          character representing the value
     * @throws UgyldigVerdiUnntak 
     */
    public static char valueToSymbol(int value, char tom) throws UgyldigVerdiUnntak {
        if (value == 0) {                           // tom
            return tom;
        } else if (1 <= value && value <= 9) {      // tegn er i [1, 9]
            return (char) (value + '0');
        } else if (10 <= value && value <= 35) {    // tegn er i [A, Z]
            return (char) (value + 'A' - 10);
        } else if (value == 36) {                   // tegn er @
            return '@';
        } else if (value == 37) {                   // tegn er #
            return '#';
        } else if (value == 38) {                   // tegn er &
            return '&';
        } else if (39 <= value && value <= 64) {    // tegn er i [a, z]
            return (char) (value + 'a' - 39);
        } else {                                    // Value not valid. Actually this will not be executed in any circumstances but implementing it for practice purposes
            throw new UgyldigVerdiUnntak(value);    
        }
    }

}
