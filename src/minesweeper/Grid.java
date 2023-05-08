package minesweeper;

public class Grid extends Cell{

	public Cell[][] grid;
	private int rows;
	private int cols;
	private int mines;
	
	public Grid(int gridRows, int gridCols, int gridMines) {
		this.rows = gridRows;
		this.cols = gridCols;
		this.setMines(gridMines);
		this.setGrid();
		
	}
	
	public Cell[][] getGrid() {
		return grid;
	}

	public void setGrid() {
		grid = new Cell[rows][cols];
	}

	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public void setMines(int gridMines) {
		this.mines = gridMines;
	}
	
	public int getMines() {
		return mines;
	}
}
