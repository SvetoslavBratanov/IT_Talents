package minesweeper;

import java.util.Random;
import java.util.Scanner;

import games.Board;
import games.Game;
import games.Player;

public class Minesweeper extends Game {
	private char[][] array;
	private boolean[][] reveal;
	private int row;
	private int col;

	public Minesweeper(String name, Player player, char[][] array, boolean[][] reveal) {
		super(name, player);
		setArray(array);
		setReveal(reveal);
	}

	public char[][] getArray() {
		return array;
	}

	public boolean[][] getReveal() {
		return reveal;
	}

	public void setArray(char[][] array) {
		this.array = array;
	}

	public void setReveal(boolean[][] reveal) {
		this.reveal = reveal;
	}

	@Override
	public void initializeMatrix() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose on what difficulty you want to play :\n\n"
				+ "Level 1 : Beginner -> 8 rows , 8 columns and 10 mines.\n"
				+ "Level 2 : Intermediate -> 16 rows , 16 columns and 49 mines.\n"
				+ "Level 3 : Expert -> 16 rows , 30 columns and 99 mines.\n"
				+ "Level 4 : Custom -> 16 rows , 30 columns and then you have to choose the number of mines you want.\n\n"
				+ "Enter the number of the level which you want to play : ");

		int level = sc.nextInt();
		array = generateLevel(level);
		fillMinesCount(array, 0, 0);
		boolean hasWon = true;
		do {
			printBoard();
			playerMove();
			if (array[this.row][this.col] == '*') {
				hasWon = false;
			}

		}

		while (reveal(reveal, array, this.col, this.row));
		if (!hasWon) {
			System.out.print("You hitted a mine. GAME OVER ! ! ! \n");
		} else {
			printWinMessage();
		}

	}

	@Override
	public void printBoard() {
		for (int a = 0; a < array[0].length; a++) {
			if (a < 10) {
				System.out.print("  " + a + "  ");
			} else {
				System.out.print("  " + a + " ");
			}
		}
		System.out.println("\n");
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (reveal[i][j]) {
					System.out.print("  " + array[i][j] + "  ");
				} else {
					System.out.print("[ x ]");
				}
			}
			System.out.print("   " + i);
			System.out.println();
		}
	}

	@Override
	public void playerMove() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the coordinates of the position you want to check :\n ");
		System.out.println("Enter row :");
		this.row = sc.nextInt();
		System.out.println("Enter col :");
		this.col = sc.nextInt();
		checkForValidateMove();
	}

	@Override
	public void botMove(char[][] field) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkForValidateMove() {
		if (this.row < 0 || this.col < 0 || this.row >= array.length || this.col >= array.length) {
			System.out.println("The coordinates you entered do not exist! \n\n");
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean checkForWin(char symbol) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void printWinMessage() {
		System.out.println("Congarulations!You won!\n");
	}

	public boolean reveal(boolean[][] revealed, char[][] array, int currentX, int currentY) {
		// check if the chosen coordinates are in the field
		if (currentX < 0 || currentX > array[0].length - 1 || currentY < 0 || currentY > array.length - 1
				|| revealed[currentY][currentX]) {
			return true;
		}
		// check if there is a opened field or a mine
		if (array[currentY][currentX] != '0' && array[currentY][currentX] != '*') {
			revealed[currentY][currentX] = true;
			return true;
			// if its mine we return false and game ends.
		} else if (array[currentY][currentX] == '*') {
			return false;
			// Recursively we show the positions which are near the selected
			// field
		} else {
			revealed[currentY][currentX] = true;
			reveal(revealed, array, currentX, currentY - 1);
			reveal(revealed, array, currentX, currentY + 1);
			reveal(revealed, array, currentX - 1, currentY - 1);
			reveal(revealed, array, currentX - 1, currentY + 1);
			reveal(revealed, array, currentX + 1, currentY + 1);
			reveal(revealed, array, currentX - 1, currentY + 1);
			reveal(revealed, array, currentX - 1, currentY);
			reveal(revealed, array, currentX + 1, currentY);
			return true;
		}

	}

	public void fillMinesCount(char[][] minesArray, int currentMinesX, int currentMinesY) {

		int lastIndexX = minesArray[0].length - 1;
		int lastIndexY = minesArray.length - 1;

		// check if the chosen coordinates are in the field
		if (currentMinesX < 0 || currentMinesX > lastIndexX || currentMinesY < 0 || currentMinesY > lastIndexY
				|| minesArray[currentMinesY][currentMinesX] != 0) {
			return;
		}

		int minesCount = 0;

		// Up
		if (currentMinesY != 0 && minesArray[currentMinesY - 1][currentMinesX] == '*') {
			minesCount++;
		}
		// Down
		if (currentMinesY != lastIndexY && minesArray[currentMinesY + 1][currentMinesX] == '*') {
			minesCount++;
		}
		// Up Left
		if (currentMinesY != 0 && currentMinesX != 0 && minesArray[currentMinesY - 1][currentMinesX - 1] == '*') {
			minesCount++;
		}
		// Up Right
		if (currentMinesY != 0 && currentMinesX != lastIndexX
				&& minesArray[currentMinesY - 1][currentMinesX + 1] == '*') {
			minesCount++;
		}
		// Down Left
		if (currentMinesY != lastIndexY && currentMinesX != 0
				&& minesArray[currentMinesY + 1][currentMinesX - 1] == '*') {
			minesCount++;
		}
		// Down Right
		if (currentMinesY != lastIndexY && currentMinesX != lastIndexX
				&& minesArray[currentMinesY + 1][currentMinesX + 1] == '*') {
			minesCount++;
		}
		// Left
		if (currentMinesX != 0 && minesArray[currentMinesY][currentMinesX - 1] == '*') {
			minesCount++;
		}
		// Right
		if (currentMinesX != lastIndexX && minesArray[currentMinesY][currentMinesX + 1] == '*') {
			minesCount++;
		}
		minesArray[currentMinesY][currentMinesX] = (char) (minesCount + '0');

		// Up
		fillMinesCount(minesArray, currentMinesX, currentMinesY - 1);

		// Down
		fillMinesCount(minesArray, currentMinesX, currentMinesY + 1);

		// Up Left
		fillMinesCount(minesArray, currentMinesX - 1, currentMinesY - 1);

		// Up Right
		fillMinesCount(minesArray, currentMinesX - 1, currentMinesY + 1);

		// Down Right
		fillMinesCount(minesArray, currentMinesX + 1, currentMinesY + 1);

		// Down Left
		fillMinesCount(minesArray, currentMinesX - 1, currentMinesY + 1);

		// Left
		fillMinesCount(minesArray, currentMinesX - 1, currentMinesY);

		// Right
		fillMinesCount(minesArray, currentMinesX + 1, currentMinesY);

	}

	public char[][] generateMines(int rows, int cols, int minesCount) {
		char[][] minesArray = new char[rows][cols];
		Random random = new Random();
		while (minesCount > 0) {
			int mineX = random.nextInt(cols);
			int mineY = random.nextInt(rows);

			if (minesArray[mineX][mineY] != '*') {
				minesArray[mineY][mineX] = '*';
				minesCount--;
			}
		}
		return minesArray;
	}

	char[][] generateLevel(int level) {
		Scanner sc = new Scanner(System.in);
		int rows = 0;
		int cols = 0;
		int minesCount = 0;
		switch (level) {
		case 1:
			System.out.println("You have selected level 1:");
			cols = 8;
			rows = 8;
			minesCount = 10;
			array = new char[rows][cols];
			reveal = new boolean[rows][cols];
			break;
		case 2:
			System.out.println("You have selected level 2:");
			rows = 16;
			cols = 16;
			minesCount = 49;
			array = new char[rows][cols];
			reveal = new boolean[rows][cols];
			break;
		case 3:
			System.out.println("You have selected level 3:");
			rows = 16;
			cols = 30;
			minesCount = 99;
			array = new char[rows][cols];
			reveal = new boolean[rows][cols];
			break;
		case 4:
			System.out.println("You have selected level 4:");
			System.out.println("Please enter, how many mines you want:");
			minesCount = sc.nextInt();
			rows = 16;
			cols = 30;
			array = new char[rows][cols];
			reveal = new boolean[rows][cols];
			break;
		}
		return generateMines(rows, cols, minesCount);
	}
}
