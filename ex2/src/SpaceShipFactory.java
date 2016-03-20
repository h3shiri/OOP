import oop.ex2.*;

public class SpaceShipFactory {
	/** the valid selection set of the various types of ships */
	public static String[] validSet = {"h","r","b","a","d","s"};

	/**
	 * checks the validity of the user input.
	 * including the condition that only one Human SpaceShip shall exist per game.
	 * @param args - the arguments for the various ships (chars)
	 * @return true if the input is from the valid set of types and there is at most one HumanSpaceShip.
	 */
	private static boolean lagalInput(String[] args){
		int humanCounter = 0;

		for(String letter : args){
			boolean legal = false;
        	for(String type : validSet){
        		if(letter.equals(type)){
        			if(type.equals("h"))
        				humanCounter++;
        			legal = true;
        			break;
        		}
        	}
        	/** in case we have encountered an invalid letter */
        	if (!legal){
        		return false;
        	}        	
        }
        /** in case of more then one human SpaceShip */
		return (humanCounter <= 1);
	}

    public static SpaceShip[] createSpaceShips(String[] args) {

    	int i = 0;
    	SpaceShip[] res;
    	res = new SpaceShip[args.length];
    	for (String letter : args) {
    		switch (letter){
    			case "h":
   					res[i] = new HumanSpaceShip();
    				i++;
    				break;
    			case "r":
    				res[i] = new RunnerSpaceShip();
    				i++;
    				break;
    			case "b":
    				res[i] = new BasherSpaceShip();
    				i++;
    				break;
    			case "a":
    				res[i] = new AggressiveSpaceShip();
    				i++;
    				break;
    			case "d":
    				res[i] = new DrunkardSpaceShip();
    				i++;
    				break;
    			case "s":
    				res[i] = new SpecialSpaceShip();
    				i++;
    				break;
    		}
    	}
		return res;
    }
}
