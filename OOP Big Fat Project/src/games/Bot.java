package games;

import java.util.Random;

import games.IMove;
@SuppressWarnings("unused")
public class Bot implements IMove {
	
	private final String name;
	
	public Bot () {
		name = "Computer";
	}

	public String getName() {
		return name;
	}
	public void makeMove(char[][] field, int row, int col, char newSymbol) {
		field[row][col] = newSymbol;
	}
}
