package minesweeper;

public class Game{
	public static Cell[][] grid;
	
	private int gridRows;
	private int gridCols;
	
	public Game(int gridRows, int gridCols, int gridMines) {
		
		
		
		grid = new Cell[gridRows][gridCols];
		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridCols; j++) {
				grid[i][j] = new Cell();
			}
		}
		this.gridRows = gridRows;
		this.gridCols = gridCols;
		
		// mines randomize
		int count = 0;
	    while (count < gridMines) {
	        int row = (int) (Math.random() * gridRows);
	        int col = (int) (Math.random() * gridCols);
	        if (!grid[row][col].isMine()) {
	            grid[row][col].setMine();
	            updateNext(row, col);
	            count++;
	        }
	    }
	    
	}
	
	// update adjacent cells when mine is set
	private void updateNext(int row, int col) {
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {		
				
				boolean isInBounds = i >= 0 && i < gridRows && j >= 0 && j < gridCols;
				
				if (isInBounds) {
					Cell currentCell = grid[i][j];
					
					if (!currentCell.isMine() && isInBounds) {
						currentCell.setValue(currentCell.getValue() + 1);
					}
				}
			}
		}
	}
	
	// after user input, open cell from input
	public void open(int row, int col) {
		if (!grid[row][col].isOpen()) {
			grid[row][col].setOpen();
			if (grid[row][col].getValue() == 0) {
				openNext(row, col);
			}
		}
	}
	
	// open cells around if cell opened is 0
	private void openNext(int row, int col) {
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				
				boolean isInBounds = i >= 0 && i < gridRows && j >= 0 && j < gridCols;
				
				if (isInBounds) {
					open(i, j);
				}
			}
		}
	}
	
	public void openAll() {
		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridCols; j++) {
				Cell cell = grid[i][j];
				if (cell.isOpen() == false || cell.isMine()) {
					cell.setOpen();
				}
			}
		}
	}
	
	public boolean checkWin() {
		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridCols; j++) {
				Cell cell = grid[i][j];
				if (!cell.isOpen() && !cell.isMine()) {
					return false;
				}
			}
		}
		return true;
	}
	
	// code to display grid
	public void display() {
		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridCols; j++) {
				if (grid[i][j].isOpen()) {
					if (grid[i][j].isMine()) {
						System.out.print("M ");
					} else {
						System.out.print(grid[i][j].getValue() + " ");
					}
				} else {
					System.out.print("? ");
				}
			}
			System.out.println();
		}
	}
	
	// maybe a game over function
	public boolean isGameOver(int row, int col) {
		
		Cell test = grid[row][col];
		
		if (test.isMine()) {
			return true;
		}
		return false;
	}
	
	// check remaining ? 
	public boolean isGameWon() {
		
		if (checkWin()) {
			return true;
		}
		return false;
	}
}

