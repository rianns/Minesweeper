package minesweeper;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// Set a 10 x 10 grid with 10 mines
		
		Game game = new Game(10, 10, 10);
		System.out.println("BOOTLEG MINESWEEPER\n");
		
		// display game grid
		game.display();
		
		// loop that keeps asking for user input until lose, win, or keep playing
		while (true) {
			System.out.print("\nEnter coordinates (row column): ");	
			int row = input.nextInt() - 1;
			int col = input.nextInt() - 1;
			game.open(row, col);	
			
			if (game.isGameOver(row, col)) {
				game.openAll();
				game.display();
				System.out.println("\nBOOM!\n");
				break;
			} else if (game.isGameWon()) {
				game.openAll();
				game.display();
				System.out.println("\nYOU WIN!\n");
				break;
			} else {
				game.display();
			}
		}

		input.close();
	}

}
