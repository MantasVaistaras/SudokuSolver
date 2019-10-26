public class Board {
	
	private int rowsInBox;
	private int columnsInBox;
	private int sizeOfTheBox;
	private Region[] regions;
	private Row[] rows;
	private Column[] columns;
	private Box[] boxes;
	private SudokuContainer solutions;
	
	public Board(int rowsInBox, int columnsInBox) {
		this.rowsInBox = rowsInBox;
		this.columnsInBox = columnsInBox;
		this.sizeOfTheBox = rowsInBox*columnsInBox;
		this.regions = new Region[this.sizeOfTheBox*this.sizeOfTheBox];
	}
	
	public void createSudokuContainer() {
		this.solutions = new SudokuContainer();
	}
	
	public void setSolution(Board board) {
		this.solutions.setSolution(board);
	}
	
	public SudokuContainer getSudokuContainer() {
		return this.solutions;
	}
	
	public void addRegion(Region region, int i) {
		this.regions[i] = region;
	}
	
	public int getRowsInBox() {
		return this.rowsInBox;
	}
	
	public int getColumnsInBox() {
		return this.columnsInBox;
	}
	
	public Box[] getBoxes() {
		return this.boxes;
	}
	
	public Column[] getColumns() {
		return this.columns;
	}
	
	public void printBoard(){
		for (int i = 0; i < regions.length; i++) {
			if(i%this.columnsInBox == 0 & i != 0 & i%this.sizeOfTheBox != 0) {
				System.out.print("|");
			}
			if(i%this.sizeOfTheBox == 0 & i != 0) {
				System.out.println();
			}
			if(i%(this.sizeOfTheBox*this.rowsInBox) == 0 & i!= 0) {
				for (int q = 0; q < this.rowsInBox; q++) {
					for (int j = 0; j < this.columnsInBox; j++) {
						System.out.print("-");
					}
					if(q != (this.rowsInBox - 1)) {
						System.out.print("+");
					}
				}
				
				System.out.println();

			}
			System.out.print(regions[i].print());
			
		}
		System.out.println();
	}
	
	public int getSizeOfTheBox() {
		return sizeOfTheBox;
	}
	
	public Region getRegion(int i) {
		return this.regions[i];
	}
	
	public Region[] getRegions() {
		return this.regions;
	}
		
	public void createDataStructure() {
		this.rows = new Row[this.sizeOfTheBox];
		this.columns = new Column[this.sizeOfTheBox];
		this.boxes = new Box[this.sizeOfTheBox];
		
		int k = 0;
		int boxID = 0;
		int regionIDInThatBox = 0;
		for (int i = 0; i < this.sizeOfTheBox; i++) {
			this.rows[i] = new Row(i, this.sizeOfTheBox);
			for (int j = 0; j < this.sizeOfTheBox; j++) {
				if( i == 0) {
					this.columns[j] = new Column(j, this.sizeOfTheBox);
				}
				this.rows[i].setRegion(this.regions[i*this.sizeOfTheBox+j], j);
				this.columns[j].setRegion(this.regions[i*this.sizeOfTheBox+j], i);
				if( j%this.columnsInBox == 0 & i%this.rowsInBox ==0 ) {
					this.boxes[k] = new Box(k, this.sizeOfTheBox);
					k++;
				}
				boxID = j/this.columnsInBox + (i/this.rowsInBox)*this.rowsInBox;
				regionIDInThatBox = j%this.columnsInBox + i%this.rowsInBox*this.columnsInBox;
				this.boxes[boxID].setRegion(this.regions[i*this.sizeOfTheBox+j], regionIDInThatBox);
				
				this.regions[i*this.sizeOfTheBox+j].setColumn(this.columns[j]);
				this.regions[i*this.sizeOfTheBox+j].setRow(this.rows[i]);
				this.regions[i*this.sizeOfTheBox+j].setBox(this.boxes[boxID]);
			}
		}
	}
	

}
