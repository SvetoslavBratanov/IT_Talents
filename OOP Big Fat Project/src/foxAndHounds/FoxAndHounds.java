package foxAndHounds;

import java.util.Scanner;

import games.Game;
import games.Player;

public class FoxAndHounds extends Game {
	private char[][] field;
	private int row;
	private int col;

	public FoxAndHounds(String name, Player player, char[][] field) {
		super(name, player);
		setField(field);
	}
	
	//Getters and setters
	public char[][] getField() {
		return field;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public void setField(char[][] field) {
		this.field = field;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public void setCol(int col) {
		this.col = col;
	}
	//Methods
	@Override
	public void initializeMatrix() {

	}

	@Override
	public void run() {
		do {
			FoxAndHoundsBoard board = new FoxAndHoundsBoard();
			board.printBoard();
			field=new char[8][8];
			playerMove();
			board.field=field;
		}
		while(!isGameOver());
	}

	@Override
	public void printBoard() {
	}

	@Override
	public void playerMove() {
		System.out.println("Enter in which direction you want to GO!\n"
						+  "1 - Q - Press Q for UP-Right!			\n"
						+  "2-  E - Prees E for UP-Left!            \n"
						+  "3-  A - Press A for Down-Right!         \n"
						+  "4-  D - Press D for Down-Left!             ");
		Scanner sc = new Scanner(System.in);
		char move = sc.next().charAt(0);
		FoxAndHoundsPlayer player = new FoxAndHoundsPlayer();
		player.makeMove(move,this.field);
		

	}

	@Override
	public void botMove(char[][] field) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkForValidateMove() {
		return false;
	}

	@Override
	public boolean checkForWin(char symbol) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void printWinMessage() {
		// TODO Auto-generated method stub

	}
	public boolean isGameOver() {
		return false;
	}
}