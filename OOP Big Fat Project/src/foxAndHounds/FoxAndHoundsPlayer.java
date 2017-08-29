package foxAndHounds;

import games.Player;

public class FoxAndHoundsPlayer extends Player {
	private int x;
	private int y;

	public void makeMove(char a, char[][] field) {
		int[] cordinates = getCordinatesFox(field);
		switch (a) {
		case 'Q':
			this.x = cordinates[0];
			this.y = cordinates[1];
			if ((this.x == 7 && this.y == 0) || (this.x == 5 && this.y == 0) || (this.x == 3 && this.y == 0)
					|| (this.x == 1 && this.y == 0)) {
				System.out.println("You can't go into that direction!");
			} else if (checkForHoundUpLeft(this.x, this.y, field)) {
				System.out.println("There is a hound!");
			} else {
				field[this.x][this.y] = '.';
				field[this.x + 1][this.y + 1] = 'F';
			}
		case 'E':
			this.x = cordinates[0];
			this.y = cordinates[1];
			if ((this.x == 6 && this.y == 7) || (this.x == 4 && this.y == 7) || (this.x == 2 && this.y == 7)
					|| (this.x == 0 && this.y == 7)) {
				System.out.println("You can't go into that direction!");
			} else if (checkForHoundUpLeft(this.x, this.y, field)) {
				System.out.println("There is a hound!");
			} else {
				field[this.x][this.y] = '.';
				field[this.x + 1][this.y + 1] = 'F';
			}
		case 'A':
			this.x = cordinates[0];
			this.y = cordinates[1];
			if ((this.x == 7 && this.y == 0) || (this.x == 7 && this.y == 2) || (this.x == 7 && this.y == 4)
					|| (this.x == 7 && this.y == 6)) {
				System.out.println("You can't go into that direction!");
			} else if (checkForHoundUpLeft(this.x, this.y, field)) {
				System.out.println("There is a hound!");
			} else {
				field[this.x][this.y] = '.';
				field[this.x + 1][this.y + 1] = 'F';
			}
		case 'D':
			this.x = cordinates[0];
			this.y = cordinates[1];
			if ((this.x == 1 && this.y == 0) || (this.x == 3 && this.y == 0) || (this.x == 5 && this.y == 0)
					|| (this.x == 7 && this.y == 0)) {
				System.out.println("You can't go into that direction!");
			} else if (checkForHoundUpLeft(this.x, this.y, field)) {
				System.out.println("There is a hound!");
			} else {
				field[this.x][this.y] = '.';
				field[this.x + 1][this.y - 1] = 'F';
			}
		}
	}

	private int[] getCordinatesFox(char[][] field) {
		int[] cordinates = new int[2];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (field[i][j] == 'F') {
					cordinates[0] = i;
					cordinates[1] = j;
				}
			}
		}
		return cordinates;
	}

	private boolean checkForHoundUpLeft(int x, int y, char[][] field) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i == 1 && j == 0) || (i == 0) || (j == 0)) {
					continue;
				} else {
					if (field[i - 1][j - 1] == 'H') {
						return true;
					}
				}
			}
		}
		return false;
	}
}
