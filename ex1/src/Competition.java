import java.util.Scanner;

/**
 * The Competition class represents a Nim competition between two players,
 * consisting of a given number of rounds. 
 * It also keeps track of the number of victories of each player.
 */
public class Competition {
	
	/** data members */
	/** the players */
	private Player player1;
	private Player player2;
	/** indicator for displaying messages */
	private boolean displayMessage;

	/** A selction of useful counters */
	private int numOfWinsPlayer1 = 0;
	private int numOfWinsPlayer2 = 0;
	private Player currentPlayer;
	private Player previousPlayer;
	private Board currentBoard;

	/** 
	 * Receives two Player objects, representing the two competing opponents,
	 * and a flag determining whether messages should be displayed.
	 * @param player1 - the first player.
	 * @param player2 - the second player.
	 * @param displayMessage - indicates whether messages should be displayed. 
	 */
	public Competition(Player player1, Player player2, boolean displayMessage){
		this.player1 = player1;
		this.player2 = player2;
		this.displayMessage = displayMessage;
	}
	
	/** A utility function setting the current board*/
	private void setNewBoard(Board board){
		currentBoard = board;
	}

	/** 
	 * If playerPosition = 1, the results of the first player is returned.
	 * If playerPosition = 2, the result of the second player is returned.
	 * If playerPosition equals neiter, -1 is returned.
	 * @param playerPosition - which player we check
	 * @return the number of victories for the selected player.
	 */
	public int getPlayerScore(int playerPosition){
		int ERROR = -1;
		if (playerPosition == 1)
			return numOfWinsPlayer1;
		else if (playerPosition == 2) 
			return numOfWinsPlayer2;
		else
			return ERROR;
	}


	/*
	 * Returns the integer representing the type of player 1; returns -1 on bad
	 * input.
	 */
	private static int parsePlayer1Type(String[] args){
		try{
			return Integer.parseInt(args[0]);
		} catch (Exception E){
			return -1;
		}
	}
	
	/*
	 * Returns the integer representing the type of player 2; returns -1 on bad
	 * input.
	 */
	private static int parsePlayer2Type(String[] args){
		try{
			return Integer.parseInt(args[1]);
		} catch (Exception E){
			return -1;
		}
	}
	
	/*
	 * Returns the integer representing the type of player 2; returns -1 on bad
	 * input.
	 */
	private static int parseNumberOfGames(String[] args){
		try{
			return Integer.parseInt(args[2]);
		} catch (Exception E){
			return -1;
		}
	}

	/** 
	 * A utillity function that runs a single match.
	 * @param board - A fresh board to play upon.
	 * @param player1 - the first player (Player object).
	 * @param player2 - the second player (Player object).
	 * @param displayMessage - a boolean indicator whether the messages should be displayed.
	 */
	private void playAMatch(Board board, Player player1, Player player2, boolean displayMessage){
		int numOfSticksLeft = board.getNumberOfUnmarkedSticks();
		currentPlayer = player1;
		previousPlayer = player2;
		String WEL_MSG = "Welcome to the sticks game!";
		if(displayMessage)
				System.out.println(WEL_MSG);
		while(numOfSticksLeft > 0){
			String TURN_MSG = "Player "+currentPlayer.getPlayerId()+", it is now your turn!";
			if(displayMessage)
				System.out.println(TURN_MSG);
			int success = 0;
			int checkMove = -7; // arbitrary number different then success
			while(checkMove != success){
				Move currentMove = currentPlayer.produceMove(board);
				checkMove = board.markStickSequence(currentMove);
				if((checkMove != success) && displayMessage){
					String ERROR_MSG = "Invalid move. Enter another:";
					System.out.println(ERROR_MSG);
				}
				else if((checkMove == success) && displayMessage) {
					String MOVE_MSG = "Player "+currentPlayer.getPlayerId()+
					" made the move: "+currentMove.toString();
					System.out.println(MOVE_MSG);
				}
			}
			Player temp = currentPlayer;
			currentPlayer = previousPlayer;
			previousPlayer = temp;
			numOfSticksLeft = board.getNumberOfUnmarkedSticks();
		}
		int res = currentPlayer.getPlayerId();
		if(res == 1)
			numOfWinsPlayer1++;
		else {
			numOfWinsPlayer2++;
		}
		String END_MSG = "Player "+currentPlayer.getPlayerId()+" won!";
		if(displayMessage)
			System.out.println(END_MSG);
	}

	/**
	 * Run the game for the given number of rounds.
	 * @param numRounds - number of rounds to play.
	 */
	public void playMultiple(int numRounds){
		for(int i=0; i<numRounds; i++){
			playAMatch(currentBoard, player1, player2, displayMessage);
			currentBoard = new Board();
		}
		String END_MATCH_MSG = "The results are "+numOfWinsPlayer1+":"+numOfWinsPlayer2;
		System.out.println(END_MATCH_MSG);
	}


	/**
	 * The method runs a Nim competition between two players according to the three user-specified arguments. 
	 * (1) The type of the first player, which is a positive integer between 1 and 4: 1 for a Random computer
	 *     player, 2 for a Heuristic computer player, 3 for a Smart computer player and 4 for a human player.
	 * (2) The type of the second player, which is a positive integer between 1 and 4.
	 * (3) The number of rounds to be played in the competition.
	 * @param args an array of string representations of the three input arguments, as detailed above.
	 */
	public static void main(String[] args) {
		int p1Type = parsePlayer1Type(args);
		int p2Type = parsePlayer2Type(args);
		Scanner scanner1 = new Scanner(System.in);
		Player player1 = new Player(p1Type, 1, scanner1);
		Player player2 = new Player(p2Type, 2, scanner1);
		/** whether to display the messages or not */
		boolean display = true;
		if((player1.getPlayerType() != player1.HUMAN) && (player2.getPlayerType() != player2.HUMAN)){
			display = false;
		}
		Competition competition = new Competition(player1, player2, display);
		int rounds = parseNumberOfGames(args);
		String p1StrRepr = player1.getTypeName();
		String p2StrRepr = player2.getTypeName();	
		String START_MSG = "Starting a Nim competition of "+rounds+" "+"rounds between a "+p1StrRepr+
		" player and a "+p2StrRepr+" player.";
		System.out.println(START_MSG);
		Board board = new Board();
		competition.setNewBoard(board);
		competition.playMultiple(rounds);

	}	
	
}
