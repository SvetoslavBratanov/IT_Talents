package games;

public interface IPlay {
	public abstract void initializeMatrix();
	public abstract void run();
	public abstract void printBoard();
	public abstract void playerMove();
	public abstract void botMove(char[][] field);
	public abstract boolean checkForValidateMove();
	public abstract boolean checkForWin(char symbol);
	public abstract void printWinMessage();
}
