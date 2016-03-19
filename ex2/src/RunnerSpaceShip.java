import oop.ex2.*;
import java.awt.Image;
import java.awt.*;

public class RunnerSpaceShip extends SpaceShip{

	/**
     * This is called once per round by the SpaceWars game driver.
     * defines the ship behaviour, preforms according to the Runner behaviour.
     * @parm game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        
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