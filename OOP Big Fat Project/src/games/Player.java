package games;

import java.util.Scanner;

import games.IMove;

public class Player implements IMove {
	
	Scanner sc = new Scanner (System.in);
	
	private String name;
	
	public Player() {
		setName("You");
	}
	
	public Player(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name != null && !name.isEmpty()) {
			this.name = name;
		} else {
			System.out.println("Entered name is not valid!");
		}
	}
	
	public void makeMove(char[][] field, int row, int col, char newSymbol) {
		field[row][col] = newSymbol;
	}
}
