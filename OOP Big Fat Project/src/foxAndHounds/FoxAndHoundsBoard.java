package foxAndHounds;

import games.Board;

public class FoxAndHoundsBoard extends Board {
	private char hound = 'H';
	private char fox = 'F';
	private char blackSquare = '.';
	private char redSquare = ' ';

	protected FoxAndHoundsBoard() {
		this.row = 8;
		this.col = 8;
		this.field = new char[this.row][this.col];
		
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				if ((i + j) % 2 == 0) {
					field[i][j] = redSquare;
				} else if (i == 0 && j == 1) {
					field[i][j] = hound;
				} else if (i == 0 && j == 3) {
					field[i][j] = hound;
				} else if (i == 0 && j == 5) {
					field[i][j] = hound;
				} else if (i == 0 && j == 7) {
					field[i][j] = hound;
				} else if (i == 7 && j == 0) {
					field[i][j] = fox;
				} else {
					field[i][j] = blackSquare;
				}
			}
		}
	}

	public void printBoard() {
		System.out.println("______________________________________");
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				System.out.print("  "+field[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println("______________________________________");
	}
}
