package dotsAndBoxes;

import java.util.Random;
import java.util.Scanner;

import games.Board;
import games.Bot;
import games.Game;
import games.Player;

public class DotsAndBoxes extends Game {
	Scanner sc = new Scanner(System.in);
	
	public DotsAndBoxes() {
		
	}
	
	public DotsAndBoxes(String name, Player player, Bot bot, int row, int col) {
		super("Dots and Boxes", player, bot);
		super.setBoard(new Board(row * 2 - 1, col * 2 - 1));
		//super.setBoard(new Board(row, col));
		initializeMatrix();		
	}

	@Override
	public void initializeMatrix() {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.getBoard().getRow(); i++) {
            for (int j = 0; j < this.getBoard().getCol(); j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    this.getBoard().getField()[i][j] = '+';
                } else {
                   this.getBoard().getField()[i][j] = ' ';
                }
            }
        }		
	}
	
    @Override
    public void run() {
    	// TODO Auto-generated method stub
    	System.out.println();
    	this.printBoard();
        while (true) {
        	if(!isAllCellsFilled()) {
        		this.playerMove();
        	}
            if (checkForWin('X')) {
                printBoard();
                super.playerWin = true;
                printWinMessage();
                break;
            }
            if(!isAllCellsFilled()) { 
            	this.botMove(getBoard().getField());
            } else {
            	 if (checkForWin('O')) {
                     super.botWin = true;
            	 }
            	 printBoard();
            	 printWinMessage();
            	 return;
            }
            this.printBoard();
            if (checkForWin('O')) {
                super.botWin = true;
                printWinMessage();
                break;
            }
            System.out.println();
        }
    }

	@Override
	public void printBoard() {
		// TODO Auto-generated method stub
		int count = 0;
        System.out.print("  ");
        for (int i = 0; i < this.getBoard().getCol(); i++) {
            if (i % 2 == 0) {
            	System.out.print(count + " ");
            	count++;
            }
        }
        count = 0;
        System.out.println();
        for (int i = 0; i < this.getBoard().getRow(); i++) {
            if (i % 2 == 0) {
                System.out.print(count + " ");
                count++;
            } else {
                System.out.print("  ");
            }
            for (int j = 0; j < this.getBoard().getCol(); j++) {
                System.out.print(this.getBoard().getField()[i][j]);
            }
            System.out.println();
        }
        System.out.println("\n");
	}
	
	@Override
    public void playerMove() {
    	// TODO Auto-generated method stub
		int numberOfSymbols = countSymbol('X');
		this.insertCoordinates();
        fillSymbol(getBoard().getField(), 'X');    
        if(isAllCellsFilled()) {
        	return;
        }
        this.printBoard();
        if(numberOfSymbols < countSymbol('X')) {
        	playerMove();
        }
    }
	
	private void insertCoordinates() {
		int rowForFirstDot, rowForSecondDot, colForFirstDot, colForSecondDot;
        boolean isAddTittle = false;
        do {
        	System.out.println("Enter coordinates of first dot: ");
            System.out.println("Enter row: ");
            rowForFirstDot = sc.nextInt() * 2;
            System.out.println("Enter column: ");
            colForFirstDot = sc.nextInt() * 2;
            System.out.println("\nEnter coordinates of second dot: ");
            System.out.println("Enter row: ");
            rowForSecondDot = sc.nextInt() * 2;
            System.out.println("Enter column: ");
            colForSecondDot = sc.nextInt() * 2;
            if (isInBounds(rowForFirstDot, colForFirstDot, rowForSecondDot, colForSecondDot)
            		&& areDotsValid(rowForFirstDot, colForFirstDot, rowForSecondDot, colForSecondDot) 
            		&& isBlankCell(rowForFirstDot, colForFirstDot, rowForSecondDot, colForSecondDot)) {
             	this.fillCell(rowForFirstDot, colForFirstDot, rowForSecondDot, colForSecondDot);
             	isAddTittle = true;
            } else {
            	System.out.println("Wrong coordinates! Try again!");
            }        
        } while (!isAddTittle);
	}
		
	@Override
	public void botMove(char[][] field) {
        int countForBotSymbol;
        do {
            countForBotSymbol = countSymbol('O');
            if (closeCell(field)) {
                fillSymbol(getBoard().getField(), 'O');
                if(isAllCellsFilled()) {
                	return;
                }
            } else {
                makeRandomMove(field);
            }
        } while (countForBotSymbol < countSymbol('O'));     
	}
	
	private boolean closeCell(char[][] field) {
		int countForTittles = 0;
        for (int i = 1; i < getBoard().getRow(); i += 2) {
            for (int j = 1; j < getBoard().getCol(); j += 2) {
                if (field[i][j] == ' ') {
                    if (field[i - 1][j] == '-') {
                        countForTittles++;
                    }
                    if (field[i + 1][j] == '-') {
                        countForTittles++;
                    }
                    if (field[i][j - 1] == '|') {
                        countForTittles++;
                    }
                    if (field[i][j + 1] == '|') {
                        countForTittles++;
                    }
                }
                if (countForTittles == 3) {
                    if (field[i - 1][j] == ' ') {
                    	field[i - 1][j] = '-';
                        return true;
                    }
                    if (field[i + 1][j] == ' ') {
                    	field[i + 1][j] = '-';
                        return true;
                    }
                    if (field[i][j - 1] == ' ') {
                    	field[i][j - 1] = '|';
                        return true;
                    }
                    if (field[i][j + 1] == ' ') {
                    	field[i][j + 1] = '|';
                        return true;
                   }
                }
                countForTittles = 0;
            }
        }
		return false;
	}
	
	private void makeRandomMove(char[][] field) {
		System.out.println("Pravq random");
		Random r = new Random();
		int row = 0, col = 0;
        boolean isAddTittle = false;
        do {
            row = r.nextInt((this.getBoard().getRow() / 2) + 1) * 2;
            System.out.println("Row is" + row);
            col = r.nextInt((this.getBoard().getCol() / 2) + 1) * 2;
            System.out.println("Col is " + col);
            if (isInBounds(row - 2, col) && field[row - 1][col] == ' ') {
                row = row - 1;
            } else if (isInBounds(row + 2, col) && field[row + 1][col] == ' ') {
                row = row + 1;
            } else if (isInBounds(row, col - 2) && field[row][col - 1] == ' ') {
                col = col - 1;
            } else if (isInBounds(row, col + 2) && field[row][col + 1] == ' ') {
                col = col + 1;
            }
            if (isBlankCell(row, col)) {
                if (row % 2 == 0) {
                	field[row][col] = '-';
                } else {
                	field[row][col] = '|';
                }
                isAddTittle = true;
            }
        } while (!isAddTittle);
        System.out.println("\n");
	}

	@Override
	public boolean checkForValidateMove() {
		// TODO Auto-generated method stub
		return false;
	}
		
	@Override
	public boolean checkForWin(char symbol) {
		// TODO Auto-generated method stub
		if (isAllCellsFilled()) {
            int countOfSymbols = 0;
            for (int i = 1; i < this.getBoard().getRow(); i += 2) {
                for (int j = 1; j < this.getBoard().getCol(); j += 2) {
                   if (this.getBoard().getField()[i][j] == symbol) {
                        countOfSymbols++;
                   }
                }
            }
            if(countOfSymbols >= ((getBoard().getRow() / 2) 
            		* (getBoard().getCol()/ 2)) / 2 + 1) {
            	return true;
            }
		}
		return false;
	}

	@Override
	public void printWinMessage() {
		// TODO Auto-generated method stub
		if(super.playerWin) {
			System.out.println(this.getPlayer().getName() + " WIN!\n");
		} else if(super.botWin) {
			System.out.println(this.getBot().getName() + " WIN!");
			System.out.println("Game over!\n");
		} else {
			System.out.println("DRAW!\n");
		}		
	}

    private boolean isAllCellsFilled() {
       for (int i = 1; i < this.getBoard().getRow(); i += 2) {
            for (int j = 1; j < this.getBoard().getCol(); j += 2) {
                if (this.getBoard().getField()[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private int countSymbol(char symbol) {
        int countOfSymbols = 0;
        for (int i = 1; i < this.getBoard().getRow(); i += 2) {
            for (int j = 1; j < this.getBoard().getCol(); j += 2) {
                if (this.getBoard().getField()[i][j] == symbol) {
                    countOfSymbols++;
                }
            }
        }
        return countOfSymbols;
    }

    private void fillSymbol(char[][] field, char symbol) {
    	for (int row = 1; row < field.length - 1; row += 2) {
            for (int col = 1; col < field[row].length - 1; col += 2) {
                if (field[row][col] == ' ') {
                   if (field[row - 1][col] == '-'
                            && field[row + 1][col] == '-'
                            && field[row][col + 1] == '|'
                            && field[row][col - 1] == '|') {
                	   getBoard().getField()[row][col] = symbol;
                   }
                }
            }
        }
    }
 
    private boolean isInBounds(int row, int col, int row2, int col2) {
        return row >= 0 && row < this.getBoard().getRow()
        		&& row2 >= 0 && row2 < this.getBoard().getRow()
        		&& col >= 0 && col < this.getBoard().getCol()
                && col2 >= 0 && col2 < this.getBoard().getCol();

    }
    
    private boolean isBlankCell(int row, int col, int row2, int col2) {
        if (row > row2) {
            return this.getBoard().getField()[row - 1][col] == ' ';
        }
        if (row2 > row) {
            return this.getBoard().getField()[row2 - 1][col] == ' ';
        }
        if (col > col2) {
            return this.getBoard().getField()[row][col - 1] == ' ';
        }
        if (col2 > col) {
            return this.getBoard().getField()[row][col2 - 1] == ' ';
       }
        return false;
    }
    
    private boolean areDotsValid(int row, int col, int row2, int col2) {
    	if(getBoard().getField()[row][col] == '+'
    			&& getBoard().getField()[row2][col2] == '+'
    			&& ((Math.abs(row - row2) == 2 && col == col2) 
    					^ (Math.abs(col - col2) == 2 && row == row2))) {
    		return true;
    	}
    	return false;
    }
    
    private void fillCell(int row, int col, int row2, int col2) {
        if (row == row2) {
             if (col > col2) {
                 this.getBoard().getField()[row][col - 1] = '-';
             } else if (col2 > col) {
                 this.getBoard().getField()[row][col + 1] = '-';
             }
         } else {
             if (row > row2) {
                 this.getBoard().getField()[row - 1][col] = '|';
             } else if (row2 > row) {
                 this.getBoard().getField()[row + 1][col] = '|';
             }
         }
     }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < this.getBoard().getRow() 
        		&& col >= 0 && col < this.getBoard().getCol();
    }

    private boolean isBlankCell(int row, int col) {
        return this.getBoard().getField()[row][col] == ' ';
   }  
}
