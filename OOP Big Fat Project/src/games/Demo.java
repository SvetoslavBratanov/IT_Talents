package games;

import java.util.Scanner;

import dotsAndBoxes.DotsAndBoxes;
import ticTacToe.TicTacToe;


public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		Game game;
		int choice = 0;

		do {
			printMenu();
			choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				game = new TicTacToe(new Player(), new Bot());
				game.run();
				break;
			case 2:
				int row,col;
				do {
					System.out.println("Please enter number of rows:");
					row = sc.nextInt();
					System.out.println("Please enter number of cols:");
					col = sc.nextInt();
				} while (!validateDotsAndBoxes(row, col));			
				game = new DotsAndBoxes("Dots and Boxes", new Player(), new Bot(), row, col);
				game.run();
				break;
			case 3:
				//game = new Minesweeper();
				break;
			case 4:
				//game = new FoxAndHounds();
				break;
			}
		
		} while (choice != 5);
		
		System.out.println();
		System.out.println("Goodbye!");
		System.out.println();
		
		sc.close();
		
	}

	
	public static void printMenu() {
		System.out.println("Choose the game:\n");
		System.out.println("1 - Tic Tac Toe");
		System.out.println("2 - Dots and Boxes");
		System.out.println("3 - Minesweeper");
		System.out.println("4 - Fox and Hounds");
		System.out.println("5 - Exit");
		System.out.println();
	}
	
	public static boolean validateDotsAndBoxes(int row, int col) {
		if(row > 1 && col > 1 && row < 11 && col < 11) {
			return true;
		}
		return false;
	}		
}


