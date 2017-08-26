package games;

import java.util.Scanner;

public class Board {
	Scanner sc = new Scanner(System.in);
	private int row;
	private int col;
	private char[][] field;

	public Board() {
		
	}
	
	public Board(int row, int col) {
		setRow(row);
		setCol(col);
		if(row < 0 || col < 0) {
			System.out.println("Board not created!");
			return;
		}
		this.field = new char[row][col];
	}
	
	
	public char[][] getField() {
		return field;
	}

	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		if(row >= 0) {
			this.row = row;
		} else {
			System.out.println("Entered row is not valid!");
		}
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		if(col >= 0) {
			this.col = col;
		} else {
			System.out.println("Entered col is not valid!");
		}
	}
	
	
}
