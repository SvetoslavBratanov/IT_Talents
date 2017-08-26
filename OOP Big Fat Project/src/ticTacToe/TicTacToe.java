package ticTacToe;

import java.util.Random;
import java.util.Scanner;

import games.Board;
import games.Game;
import games.Bot;
import games.Player;

public class TicTacToe extends Game {
	Scanner sc = new Scanner(System.in);
	
	public TicTacToe() {
		
	}
	
	public TicTacToe(Player player, Bot bot) {
		super("Tic Tac Toe", player, bot);
		super.setBoard(new Board(3,3));
		initializeMatrix();	
	}
	
	@Override
	public void initializeMatrix() {
		for (int row = 0; row < getBoard().getField().length; row++) {
			for (int col = 0; col < getBoard().getField()[row].length; col++) {
				getBoard().getField()[row][col] = ' ';
			}
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int countOfMoves = 0;
		printBoard();
		while (true) {
			do {
				playerMove();
			} while (!checkForValidateMove());
			countOfMoves++;
			this.getPlayer().makeMove(this.getBoard().getField(),getBoard().getRow(), getBoard().getCol(), 'X');
            if (checkForWin('X')) {
            	this.printBoard();
                super.playerWin = true;
                printWinMessage();
                break;
            }           
            if(countOfMoves == 9) {
            	this.printBoard();
            	printWinMessage();
            	break;
            }    
			botMove(getBoard().getField());
            countOfMoves++;
            this.printBoard();
            if (checkForWin('O')) {
            	super.botWin = true;
            	printWinMessage();
                break;
            }           
        }
	}
	
	@Override
	public void printBoard() {
		System.out.print("  ");		
		for (int col = 0; col < getBoard().getField()[0].length; col++) {
			System.out.print("  " + (col + 1) + " ");
		}	
		System.out.println();		
		int lineLenght = getBoard().getField()[0].length * 4 + 1; // The length of the lines between the rows 
		printHorizontalLine(lineLenght);	
		for (int row = 0; row < getBoard().getField().length; row++) {
			System.out.print((row + 1) + " ");		
			for (int col = 0; col < getBoard().getField()[row].length; col++) {
				System.out.print("| " + getBoard().getField()[row][col] + " ");
			}		
			System.out.println("|");
			printHorizontalLine(lineLenght);
		}		
		System.out.println();		
	}
	
	private void printHorizontalLine(int lineLenght) {
		System.out.print("  "); // two intervals	
		for (int col = 0; col < lineLenght; col++) {
			System.out.print("-");
		}	
		System.out.println();
	}
	
	@Override
    public void playerMove() {
   		System.out.println("Enter row: ");
       	getBoard().setRow(sc.nextInt() - 1);
       	System.out.println("Enter column: ");
       	getBoard().setCol(sc.nextInt() - 1);
    }
	
	@Override
	public void botMove(char[][] field) {
		char botSymbol = 'O';
		char playerSymbol = 'X';
		char tempBotSymbol = botSymbol;
		
		if(getWin(field, botSymbol, tempBotSymbol)) {
			return;
		} 
		else if (checkToBeSureComputerDoesNotLose(field, playerSymbol, tempBotSymbol)) {
			return;
		} 
		//If you are brave enough, remove this comment, the method speaks for itself!
//		else if (youWillNeverWin(field, botSymbol, playerSymbol)) {
//			return;
//		}
		else if(field[1][1] == ' '){
			field[1][1] = botSymbol;          
        }
        else if(field[0][0] == ' '){
        	field[0][0] = botSymbol;                
        }
        else {
        	makeRandomMove(field, botSymbol);
        }	
	}
	
	@Override
	public boolean checkForValidateMove() {	
		if (getBoard().getRow() < 0 || getBoard().getRow() > 2) {
			return false;
		}	
		if (getBoard().getCol() < 0 || getBoard().getCol() > 2) {
			return false;
		}	
		if (getBoard().getField()[getBoard().getRow()][getBoard().getCol()] != ' ') {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean checkForWin(char symbol) {
		if (getBoard().getField()[0][0] == symbol && getBoard().getField()[0][1] == symbol && getBoard().getField()[0][2] == symbol) {
			return true;
		}
		
		if (getBoard().getField()[1][0] == symbol && getBoard().getField()[1][1] == symbol && getBoard().getField()[1][2] == symbol) {
			return true;
		}
		
		if (getBoard().getField()[2][0] == symbol && getBoard().getField()[2][1] == symbol && getBoard().getField()[2][2] == symbol) {
			return true;
		}
		
		if (getBoard().getField()[0][0] == symbol && getBoard().getField()[1][0] == symbol && getBoard().getField()[2][0] == symbol) {
			return true;
		}
		
		if (getBoard().getField()[0][1] == symbol && getBoard().getField()[1][1] == symbol && getBoard().getField()[2][1] == symbol) {
			return true;
		}
		
		if (getBoard().getField()[0][2] == symbol && getBoard().getField()[1][2] == symbol && getBoard().getField()[2][2] == symbol) {
			return true;
		}
		
		if (getBoard().getField()[0][0] == symbol && getBoard().getField()[1][1] == symbol && getBoard().getField()[2][2] == symbol) {
			return true;
		}
		
		if (getBoard().getField()[0][2] == symbol && getBoard().getField()[1][1] == symbol && getBoard().getField()[2][0] == symbol) {
			return true;
		}		
		return false;
	}
	
	@Override
	public void printWinMessage() {
		if(this.playerWin) {
			System.out.println(this.getPlayer().getName() + " WIN!\n");
		} else if(this.botWin) {
			System.out.println(this.getBot().getName() + " WIN!");
			System.out.println("Game over!\n");
		} else {
			System.out.println("\nDRAW!\n");
		}
	}
	
	private boolean getWin(char[][] field, char botSymbol, char tempBotSymbol){
		
		//COLS: 1,2 -> 3
        if(field[0][0] == botSymbol && field[0][1] == botSymbol && field[0][2] == ' '){
        	field[0][2] = tempBotSymbol;
        	return true;
        } else if(field[1][0] == botSymbol && field[1][1] == botSymbol && field[1][2] == ' '){
        	field[1][2] = tempBotSymbol;
        	return true;
        } else if(field[2][0] == botSymbol && field[2][1] == botSymbol && field[2][2] == ' '){
        	field[2][2] = tempBotSymbol;
        	return true;
        } 
        
        //COLS: 2,3 -> 1
        else if(field[0][1] == botSymbol && field[0][2] == botSymbol && field[0][0] == ' '){
        	field[0][0] = tempBotSymbol;
        	return true;
        } else if(field[1][1] == botSymbol && field[1][2] == botSymbol && field[1][0] == ' '){
        	field[1][0] = tempBotSymbol;
        	return true;
        } else if(field[2][1] == botSymbol && field[2][2] == botSymbol && field[2][0] == ' '){
        	field[2][0] = tempBotSymbol;
        	return true;
        }
        
        //COLS 1,3 -> 2
        else if(field[0][0] == botSymbol && field[0][2] == botSymbol && field[0][1] == ' '){
        	field[0][1] = tempBotSymbol;
        	return true;
        } else if(field[1][0] == botSymbol && field[1][2] == botSymbol && field[1][1] == ' '){
        	field[1][1] = tempBotSymbol;
        	return true;
        } else if(field[2][0] == botSymbol && field[2][2] == botSymbol && field[2][1] == ' '){
        	field[2][1] = tempBotSymbol;
        	return true;
        }

        //ROWS 1,2 -> 3
        else if(field[0][0] == botSymbol && field[1][0] == botSymbol && field[2][0] == ' '){
        	field[2][0] = tempBotSymbol;
        	return true;
        } else if(field[0][1] == botSymbol && field[1][1] == botSymbol && field[2][1] == ' '){
        	field[2][1] = tempBotSymbol;
        	return true;
        } else if(field[0][2] == botSymbol && field[1][2] == botSymbol && field[2][2] == ' '){
        	field[2][2] = tempBotSymbol;
        	return true;
        }
        
        //ROWS 2,3 -> 1 
        else if(field[1][0] == botSymbol && field[2][0] == botSymbol && field[0][0] == ' '){
        	field[0][0] = tempBotSymbol;
        	return true;
        } else if(field[1][1] == botSymbol && field[2][1] == botSymbol && field[0][1] == ' '){
        	field[0][1] = tempBotSymbol;
        	return true;
        } else if(field[1][2] == botSymbol && field[2][2] == botSymbol && field[0][2] == ' '){
        	field[0][2] = tempBotSymbol;
        	return true;
        }

        //ROWS 1,3 -> 2
        else if(field[0][0] == botSymbol && field[2][0] == botSymbol && field[1][0] == ' '){
        	field[1][0] = tempBotSymbol;
        	return true;
        } else if(field[0][1] == botSymbol && field[1][1] == botSymbol && field[1][1] == ' '){
        	field[1][1] = tempBotSymbol;
        	return true;
        } else if(field[0][2] == botSymbol && field[2][2] == botSymbol && field[1][2] == ' '){
         	 field[1][2] = tempBotSymbol;
         	return true;
        }
        
        //Main diagonal
        else if(field[0][0] == botSymbol && field[1][1] == botSymbol && field[2][2] == ' '){
        	field[2][2] = tempBotSymbol;
        	return true;
        } else if(field[1][1] == botSymbol && field[2][2] == botSymbol && field[0][0] == ' '){
        	field[0][0] = tempBotSymbol;
        	return true;
        } else if(field[0][0] == botSymbol && field[2][2] == botSymbol && field[1][1] == ' '){
        	field[1][1] = tempBotSymbol;
        	return true;
        }

        //Second diagonal
        else if(field[0][2] == botSymbol && field[1][1] == botSymbol && field[2][0] == ' '){
        	field[2][0] = tempBotSymbol;
        	return true;
        } else if(field[2][0] == botSymbol && field[1][1] == botSymbol && field[0][2] == ' '){
        	field[0][2] = tempBotSymbol;
        	return true;
        } else if(field[2][0] == botSymbol && field[0][2] == botSymbol && field[1][1] == ' '){
        	field[1][1] = tempBotSymbol;
        	return true;
        }
        
        return false;
		
	}
	private boolean checkToBeSureComputerDoesNotLose(char[][] field, char playerSymbol, char tempBotSymbol){
		return getWin(field, playerSymbol, tempBotSymbol);
	}
	
	@SuppressWarnings("unused")
	private boolean youWillNeverWin(char [][] field, char botSymbol, char playerSymbol) {
		if(field[0][0] == playerSymbol && field[1][1] == botSymbol && field[2][2] == playerSymbol && field[1][2] == ' '){
   	 	field[1][2] = botSymbol; 
   	 	return true;
		}    
		else if(field[0][2] == playerSymbol && field[1][1] == botSymbol && field[2][0] == playerSymbol && field[1][0] == ' '){
			field[1][0] = botSymbol;
			return true;
		}
		else if(field[0][0] == botSymbol && field[1][1] == playerSymbol && field[2][2] == playerSymbol && field[0][2] == ' '){
			field[0][2] = botSymbol;
			return true;
		}
		else if(field[1][1] == botSymbol && field[0][2] == playerSymbol && field[2][1] == playerSymbol && field[2][2] == ' '){
			field[2][2] = botSymbol;
			return true;
		}
		else if(field[1][1] == botSymbol && field[1][2] == playerSymbol 
			&& (field[2][1] == playerSymbol || field[2][0] == playerSymbol) 
    		&& field[2][2] == ' '){
			field[2][2] = botSymbol;
			return true;
		}	
		else if(field[1][1] == botSymbol && field[0][0] == playerSymbol && field[2][1] == playerSymbol && field[2][0] == ' '){
			field[2][0] = botSymbol;
			return true;
		}
		else if(field[1][1] == botSymbol && field[0][0] == playerSymbol && field[1][2] == playerSymbol && field[0][2] == ' '){
			field[0][2] = botSymbol;
			return true;
		}
		else if(field[1][1] == botSymbol && field[0][0] == playerSymbol && field[2][1] == playerSymbol && field[1][0] == ' '){
			field[1][0] = botSymbol;
			return true;
		}
		return false;
	}
	
	private void makeRandomMove(char[][] field, char botSymbol) {
		Random r = new Random();
        int row, col;
        row = r.nextInt(3);
        col = r.nextInt(3);
        if(field[row][col] == ' ') {
        	field[row][col] = botSymbol;
        } else {
        	makeRandomMove(field, botSymbol);
        }
	}
}
