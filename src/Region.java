public class Region {
	
	private int ID;
	private int numericValue;	
	private Row row;
	private Column column;
	private Box box;
	private Board board;
	private int[] possibleValues;
	private int size;
	
	public Region(char value, Board board, int ID) throws ValueOutsideTheValidInterval{
		this.board = board;
		this.ID = ID;
		this.size = board.getSizeOfTheBox()*board.getSizeOfTheBox();
		try {
			this.numericValue = App.symbolToNumeric(value);
			if(this.numericValue > board.getSizeOfTheBox()) {
				throw new ValueOutsideTheValidInterval(value);
			}
		} catch (UgyldigVerdiUnntak e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public char print() {
		char ch = 0;
		try {
			ch = App.valueToSymbol(this.numericValue, ' ');
		} catch (UgyldigVerdiUnntak e) {
			e.printStackTrace();
			System.exit(0);
		}
		return ch;
	}
	
	public int getValue() {
		return this.numericValue;
	}
	
	public void setRow(Row row) {
		this.row = row;
	}
	
	public void setColumn(Column column) {
		this.column = column;
	}
	
	public void setBox(Box box) {
		this.box = box;
	}
	
	public Row getRow() {
		return this.row;
	}
	public Column getColumn() {
		return this.column;
	}
	public Box getBox() {
		return this.box;
	}
	
	public int[] findAllPossibleValues() {
		int size = this.board.getSizeOfTheBox();
		int[] values = new int[size];
		if(this.numericValue != 0) {
			System.out.println("This region has a pre-defined value");
			values[0] = this.numericValue;
			this.possibleValues = App.removeZeros(values);
			return this.possibleValues;
		}
		
		for (int i = 1; i <= size; i++) {
			for (int j = 0; j < size; j++) {
				if(this.row.getRegions()[j].getValue() == i) {
					break;
				} else if (this.column.getRegions()[j].getValue() == i) {
					break;
				} else if (this.box.getRegions()[j].getValue() == i) {
					break;
				} else if ( j == (size-1)) {
					values[i-1] = i;
				}
			}

		}
		this.possibleValues = App.removeZeros(values);
		return this.possibleValues;
		
	}
	
	public void fillThisRegionAndTheRest() {

		if(this.numericValue == 0) {
			this.possibleValues = findAllPossibleValues();
			for (int i = 0; i < this.possibleValues.length; i++) {
				this.numericValue = this.possibleValues[i];

				if(this.ID == (this.size-1)) {
					
					if(this.board.getSudokuContainer().getNumberOfSolutions() < 3500) {
						//creating new board and regions
						Board solution = new Board(this.board.getRowsInBox(), this.board.getColumnsInBox());
						for (int j = 0; j < this.size; j++) {
							Region region;
							try {
								region = new Region(App.valueToSymbol(this.board.getRegion(j).getValue(), ' '), solution, j);
								solution.addRegion(region, j);
							} catch (ValueOutsideTheValidInterval | UgyldigVerdiUnntak e) {
								e.printStackTrace();
							}
						}
						this.board.setSolution(solution);
					} else {
						this.board.getSudokuContainer().increaseSolutionCount();
					}
																
				}else {
					this.board.getRegion(this.ID + 1).fillThisRegionAndTheRest();

					if( (i+1) == (this.possibleValues.length)) {

						this.numericValue = 0;
					}
				}
			}
		}else {

			if(this.ID == (this.size-1)) {
						
				if(this.board.getSudokuContainer().getNumberOfSolutions() < 3500) {
					//creating new board and regions
					Board solution = new Board(this.board.getRowsInBox(), this.board.getColumnsInBox());
					for (int j = 0; j < this.size; j++) {
						Region region;
						try {
							region = new Region(App.valueToSymbol(this.board.getRegion(j).getValue(), ' '), solution, j);
							solution.addRegion(region, j);
						} catch (ValueOutsideTheValidInterval | UgyldigVerdiUnntak e) {
							e.printStackTrace();
						}
					}
					this.board.setSolution(solution);
				} else {
					this.board.getSudokuContainer().increaseSolutionCount();
				}
							
			}else {
				this.board.getRegion(this.ID + 1).fillThisRegionAndTheRest();
			}
		}

	}
	
	 
}

