import oop.ex2.*;
import java.awt.Image;
import java.awt.*;

public class DrunkardSpaceShip extends SpaceShip{
	int firstCounter;
	int secondCounter;

	DrunkardSpaceShip(){
		firstCounter = 7;
		secondCounter = 3;
	}
    /**
     * This is called once per round by the SpaceWars game driver.
     * defines the ship behaviour, preforms according to the drunkard behaviour.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
    	rechargeGuns();
    	int moduloBase = 8;
    	boolean up = false;
        if(!(((firstCounter + secondCounter) % moduloBase) == 0)){
        	secondCounter = ((secondCounter+1) % moduloBase);
        	if (firstCounter > 2)
        		up = true;
        	int right = -1;
        	physics.move(up, right);
        	firstCounter = ((firstCounter+1) % moduloBase);
        }
        firstCounter++;
        secondCounter++;
        if ((firstCounter % 3) == 0)
        	fire(game);
        if ((secondCounter % 20) == 0)
        	shieldOn();
        if(!shieldsCondition)
            currentEnergyLevel++;
    }

    /** 
     * returns the ships image.
     * @return Image of the computer ship
     */
    public Image getImage() {
        if (shieldsCondition) {
        	return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        }
        else
        	return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }
}