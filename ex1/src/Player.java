import java.util.Random;
import java.util.Scanner;

/**
 * The Player class represents a player in the Nim game, producing Moves as a response to a Board state. Each player 
 * is initialized with a type, either human or one of several computer strategies, which defines the move he 
 * produces when given a board in some state. The heuristic strategy of the player is already implemented. You are 
 * required to implement the rest of the player types according to the exercise description.
 * @author OOP course staff
 */
public class Player {

	//Constants that represent the different players.
	/** The constant integer representing the Random player type. */
	public static final int RANDOM = 1;
	/** The constant integer representing the Heuristic player type. */
	public static final int HEURISTIC = 2;
	/** The constant integer representing the Smart player type. */
	public static final int SMART = 3;
	/** The constant integer representing the Human player type. */
	public static final int HUMAN = 4;
	
	private static final int BINARY_LENGTH = 4;	
	//Used by produceHeuristicMove() for binary representation of board rows.
	
	private final int playerType;
	private final int playerId;
	private Scanner scanner;
	
	/**
	 * Initializes a new player of the given type and the given id, and an initialized scanner.
	 * @param type The type of the player to create.
	 * @param id The id of the player (either 1 or 2).
	 * @param inputScanner The Scanner object through which to get user input
	 * for the Human player type. 
	 */
	public Player(int type, int id, Scanner inputScanner){		
		// Check for legal player type (we will see better ways to do this in the future).
		if (type != RANDOM && type != HEURISTIC 
				&& type != SMART && type != HUMAN){
			System.out.println("Received an unknown player type as a parameter"
					+ " in Player constructor. Terminating.");
			System.exit(-1);
		}		
		playerType = type;	
		playerId = id;
		scanner = inputScanner;
	}
	
	/**
	 * @return an integer matching the player type.
	 */	
	public int getPlayerType(){
		return playerType;
	}
	
	/**
	 * @return the players id number.
	 */	
	public int getPlayerId(){
		return playerId;
	}
	
	
	/**
	 * @return a String matching the player type.
	 */
	public String getTypeName(){
		switch(playerType){
			
			case RANDOM:
				return "Random";			    
	
			case SMART: 
				return "Smart";	
				
			case HEURISTIC:
				return "Heuristic";
				
			case HUMAN:			
				return "Human";
		}
		//Because we checked for legal player types in the
		//constructor, this line shouldn't be reachable.
		return "UnknownPlayerType";
	}
	
	/**
	 * This method encapsulates all the reasoning of the player about the game. The player is given the 
	 * board object, and is required to return his next move on the board. The choice of the move depends
	 * on the type of the player: a human player chooses his move manually; the random player should 
	 * return some random move; the Smart player can represent any reasonable strategy; the Heuristic 
	 * player uses a strong heuristic to choose a move. 
	 * @param board - a Board object representing the current state of the game.
	 * @return a Move object representing the move that the current player will play according to his strategy.
	 */
	public Move produceMove(Board board){
		
		switch(playerType){
		
			case RANDOM:
				return produceRandomMove(board);
				    
			case SMART: 
				return produceSmartMove(board);
				
			case HEURISTIC:
				return produceHeuristicMove(board);
				
			case HUMAN:
				return produceHumanMove(board);

			//Because we checked for legal player types in the
			//constructor, this line shouldn't be reachable.
			default: 
				return null;			
		}
	}
	/**
	 * Produces a random move.
	 * @param board - the board we play on.
	 * @return A legal Move object.
	 */
	private Move produceRandomMove(Board board){
		Random myRandom = new Random();
		int numOfRows = board.getNumberOfRows();
		// select a random row to play
		int rowToPlay = (myRandom.nextInt(numOfRows)+1);
		// check it isn't empty
		boolean validRow = rowHasAvailableSticks(board, rowToPlay);
		while (!(validRow)){
			rowToPlay = (myRandom.nextInt(numOfRows)+1);
			validRow = rowHasAvailableSticks(board, rowToPlay);
		}
		int rowLength = board.getRowLength(rowToPlay);
		boolean choice = false;
		int leftStickSelection = 1;
		while (!choice){
			leftStickSelection = (myRandom.nextInt(rowLength)+1);
			choice = board.isStickUnmarked(rowToPlay, leftStickSelection);
		}
		int difference = (rowLength - leftStickSelection);
		// in case we we picked the stick in the end.
		if (difference == 0)
			return new Move(rowToPlay, leftStickSelection, leftStickSelection);

		boolean success = false;
		Move move = new Move(1,1,1);
		// arbitrary initial value different then success
		while (!success){
			int rightStickSelection = (myRandom.nextInt(difference) + leftStickSelection);
			move = new Move(rowToPlay, leftStickSelection, rightStickSelection);
			success = checkValidityOfMove(board, move);
		}
		return move;
	}
	
	/**
	 * testing whether a given move is legal
	 * @param board - the relevant board
	 * @param move - the spesific move we check.
	 * @return true in case of validity false otherwise.
	 */
	boolean checkValidityOfMove(Board board, Move move){
		int left = move.getLeftBound();
		int right = move.getRightBound();
		int row = move.getRow();
		boolean res = true;
		for(int i = left; i<right+1; i++){
			if (!(board.isStickUnmarked(row, i))){
				res = false;
				break;
			}
		}
		return res;
	}

	/** 
	 * A utility function that checks whether a row is empty of unmakred sticks.
	 * @param board - the relevant board.
	 * @param row - the relvant row.
	 * @return true in case there an availabe stick false otherwise.
	 */
	private boolean rowHasAvailableSticks(Board board, int row){
		int rowLength = board.getRowLength(row);
		for(int i=1; i<rowLength+1;i++){
			if (board.isStickUnmarked(row, i))
				return true;
		}
		return false;
	}

	/**
	 * Produce some intelligent strategy to produce a move.
	 * we use various euristics to select a move.
	 * @param board - A the current board object.
	 * @return a valid move.
	 */
	private Move produceSmartMove(Board board){
		if(firstEuristic(board)){

		}

		return produceRandomMove(board);
	}
	
	/** In the case of only one row with continious junk*/
	private boolean firstEuristic(Board board){
		int rows = board.getNumberOfRows();
		int numOfAvailableRows = rows;
		int targetRow = 0; // shall be selected shortly
		for (int row=1; row <rows+1; row++){
			if (rowHasAvailableSticks(board, row))
				targetRow = row;
			else
				numOfAvailableRows--;
		}
		// checks if we have only one available row to select.
		if((numOfAvailableRows == 1) && (board.getNumberOfUnmarkedSticks()>1)){
			//check for a cotinious sequences
			boolean slice = false;
			int numOfContiniousSequences = 1;
			int marker = 0;
			int temp = 1;
			for(int index = 1; index < board.getRowLength(targetRow)+1; index++){
				if((board.isStickUnmarked(targetRow, index)) && (marker == 0))
					marker = index;
				if (board.isStickUnmarked(targetRow, index))
					temp = index;
				if ((index > temp) && (marker != 0) && (!board.isStickUnmarked(targetRow, index)))
					slice = true;
				if ((slice) && (board.isStickUnmarked(targetRow, index))){
					numOfContiniousSequences++;
					marker = index;
					slice = false;
				}
			}
			if (numOfContiniousSequences <= 2)
				return true;
		}
		return false;	
	}
	/*
	 * Interact with the user to produce his move.
	 */
	private Move produceHumanMove(Board board){
		int OPTION_I = 1;
		int OPTION_II = 2;
		// TODO: check the messages format.
		// arbitrary choice before selecting the real return value.
		Move move = new Move(1,1,1);

		boolean validSelection = false;
		while(!validSelection){
			String initialMsg = "Press 1 to display the board. Press 2 make a move:";
			System.out.println(initialMsg);
			int userInput = scanner.nextInt();
			if (userInput == OPTION_I)
				System.out.println(board);
			else if (userInput == OPTION_II){
				// TODO: check whether they excpect me to verify its a valid move on the board?
				String ROW_MSG = "Enter the row number:";
				System.out.println(ROW_MSG);
				int row = scanner.nextInt();
				String LEFT_STICK_MSG = "Enter the index of the leftmost stick:";
				System.out.println(LEFT_STICK_MSG);
				int leftStick = scanner.nextInt();
				String RIGHT_STICK_MSG = "Enter the index of the rightmost stick:";
				System.out.println(RIGHT_STICK_MSG);
				int rightStick = scanner.nextInt();
				move = new Move(row, leftStick, rightStick);
				validSelection = true;
			}
			else{
				String ERROR_MSG = "Unsupported command";
				System.out.println(ERROR_MSG);
			}
			}
		return move;	
		}
	
	/*
	 * Uses a winning heuristic for the Nim game to produce a move.
	 */
	private Move produceHeuristicMove(Board board){

		int numRows = board.getNumberOfRows();
		int[][] bins = new int[numRows][BINARY_LENGTH];
		int[] binarySum = new int[BINARY_LENGTH];
		int bitIndex,higherThenOne=0,totalOnes=0,lastRow=0,lastLeft=0,lastSize=0,lastOneRow=0,lastOneLeft=0;
		
		for(bitIndex = 0;bitIndex<BINARY_LENGTH;bitIndex++){
			binarySum[bitIndex] = 0;
		}
		
		for(int k=0;k<numRows;k++){
			
			int curRowLength = board.getRowLength(k+1);
			int i = 0;
			int numOnes = 0;
			
			for(bitIndex = 0;bitIndex<BINARY_LENGTH;bitIndex++){
				bins[k][bitIndex] = 0;
			}
			
			do {
				if(i<curRowLength && board.isStickUnmarked(k+1,i+1) ){
					numOnes++;
				} else {
					
					if(numOnes>0){
						
						String curNum = Integer.toBinaryString(numOnes);
						while(curNum.length()<BINARY_LENGTH){
							curNum = "0" + curNum;
						}
						for(bitIndex = 0;bitIndex<BINARY_LENGTH;bitIndex++){
							bins[k][bitIndex] += curNum.charAt(bitIndex)-'0'; //Convert from char to int
						}
						
						if(numOnes>1){
							higherThenOne++;
							lastRow = k +1;
							lastLeft = i - numOnes + 1;
							lastSize = numOnes;
						} else {
							totalOnes++;
						}
						lastOneRow = k+1;
						lastOneLeft = i;
						numOnes = 0;
					}
				}
				i++;
			}while(i<=curRowLength);
			
			for(bitIndex = 0;bitIndex<BINARY_LENGTH;bitIndex++){
				binarySum[bitIndex] = (binarySum[bitIndex]+bins[k][bitIndex])%2;
			}
		}
		//We only have single sticks
		if(higherThenOne==0){
			return new Move(lastOneRow,lastOneLeft,lastOneLeft);
		}
		//We are at a finishing state				
		if(higherThenOne<=1){
			if(totalOnes == 0){
				return new Move(lastRow,lastLeft,lastLeft+(lastSize-1) - 1);
			} else {
				return new Move(lastRow,lastLeft,lastLeft+(lastSize-1)-(1-totalOnes%2));
			}
		}
		for(bitIndex = 0;bitIndex<BINARY_LENGTH-1;bitIndex++){
			if(binarySum[bitIndex]>0){
				int finalSum = 0,eraseRow = 0,eraseSize = 0,numRemove = 0;
				for(int k=0;k<numRows;k++){
					
					if(bins[k][bitIndex]>0){
						eraseRow = k+1;
						eraseSize = (int)Math.pow(2,BINARY_LENGTH-bitIndex-1);
						
						for(int b2 = bitIndex+1;b2<BINARY_LENGTH;b2++){
							
							if(binarySum[b2]>0){
								
								if(bins[k][b2]==0){
									finalSum = finalSum + (int)Math.pow(2,BINARY_LENGTH-b2-1);
								} else {
									finalSum = finalSum - (int)Math.pow(2,BINARY_LENGTH-b2-1);
								}
							}	
						}
						break;
					}
				}
				numRemove = eraseSize - finalSum;
				//Now we find that part and remove from it the required piece
				int numOnes=0,i=0;
				while(numOnes<eraseSize){
					if(board.isStickUnmarked(eraseRow,i+1)){
						numOnes++;
					} else {
						numOnes=0;
					}
					i++;
				}
				return new Move(eraseRow,i-numOnes+1,i-numOnes+numRemove);
			}
		}
		//If we reached here, and the board is not symmetric, then we only need to erase a single stick
		if(binarySum[BINARY_LENGTH-1]>0){
			return new Move(lastOneRow,lastOneLeft,lastOneLeft);
		}
		/**If we reached here, it means that the board is already symmetric, 
		and then we simply mark one stick from the last sequence we saw:*/
		return new Move(lastRow,lastLeft,lastLeft);		
	}
	// TODO: remove garbage main
	// public static void main(String[] args) {
	// 	Board board = new Board();
	// 	Scanner scanner = new Scanner(System.in);
	// 	// Player player = new Player(1, 1, scanner);
	// 	Player player = new Player(4, 1, scanner);
	// 	for(int i=0;i<10;i++){
	// 		Move move = player.produceMove(board);
	// 		int d = board.markStickSequence(move);
	// 		System.out.println(move);
	// 		boolean trail = player.firstEuristic(board);
	// 		System.out.println("check 1st Euristic:"+trail);
	// 	}
	// 	System.out.println(board);
	}
}
