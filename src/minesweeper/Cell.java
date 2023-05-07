package minesweeper;

public class Cell {
	private boolean open;
	private boolean mine;
	private int value;
	
	public Cell() {
		open = false;
		mine = false;
		value = 0;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen() {
		this.open = true;
	}

	public boolean isMine() {
		return mine;
	}

	public void setMine() {
		this.mine = true;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
}