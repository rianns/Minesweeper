package minesweeper;

public class Game{
	public static Cell[][] grid;

	private int rows;
	private int cols;
	private int mines;
	
	public Game(int gridRows, int gridCols, int gridMines) {
		Grid newGrid = new Grid(gridRows, gridCols, gridMines);
		grid = newGrid.getGrid();
		
		rows = newGrid.getRows();
		cols = newGrid.getCols();
		mines = newGrid.getMines();
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grid[i][j] = new Cell();
			}
		}
		setMines();
	    
	}
	
	private void setMines() {
		int count = 0;
	    while (count < mines) {
	        int row = (int) (Math.random() * rows);
	        int col = (int) (Math.random() * cols);
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
				
				boolean isInBounds = i >= 0 && i < rows && j >= 0 && j < cols;
				
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
				
				boolean isInBounds = i >= 0 && i < rows && j >= 0 && j < cols;
				
				if (isInBounds) {
					open(i, j);
				}
			}
		}
	}
	
	public void openAll() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Cell cell = grid[i][j];
				if (cell.isOpen() == false || cell.isMine()) {
					cell.setOpen();
				}
			}
		}
	}
	
	public boolean checkWin() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
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
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
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
	
	// game over function
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

