import java.util.ArrayList;
import java.util.Scanner;

//counts how many solutions there are but stores maximum 3500

public class SudokuContainer {
	
	private ArrayList<Board> solutions;
	private int countOfSolutions = 0;
	
	public SudokuContainer() {
		this.solutions = new ArrayList<Board>();
	}
	
	public int getNumberOfSolutions() {
		return this.countOfSolutions;
	}
	
	public void setSolution(Board board) {
		this.solutions.add(board);
		this.countOfSolutions++;
	}
	
	public void increaseSolutionCount() {
		this.countOfSolutions++;
	}
	

	public void printSolutions() {
		System.out.println();
		System.out.println("This board has " + this.countOfSolutions + " solution(s)");
		Scanner in = new Scanner(System.in);	
		
		for (int i = 0; i < this.solutions.size(); i++) {
			System.out.println("Press ENTER to see the slotution " + (i+1) + "/" + this.solutions.size());
			in.nextLine();
			this.solutions.get(i).printBoard();
			System.out.println();
		}
		
		in.close();
	}

}
