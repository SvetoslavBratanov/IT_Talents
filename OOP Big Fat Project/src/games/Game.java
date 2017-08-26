package games;



public abstract class Game implements IPlay {
	
	private String name;
	private Board board;
	private Player player;
	private Bot bot;
	protected boolean playerWin = false;
	protected boolean botWin = false;
	
	public Game() {
		System.out.println("Let start!\n");
	}
	
	public Game(String name, Player player){
		this();
		this.setName(name);
		this.setPlayer(player);
		
	}
	
	public Game(String name, Player player, Bot bot) {
	        this(name, player);
	        this.setBot(bot);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name != null && !name.isEmpty()) {
			this.name = name;
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		if(player != null) {
			this.player = player;
		}
	}

	public Bot getBot() {
		return bot;
	}

	public void setBot(Bot bot) {
		if(bot != null) {
			this.bot = bot;
		}
	}

	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		if(board != null) {
			this.board = board;
		}
	}

	

}
