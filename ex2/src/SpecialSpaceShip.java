import oop.ex2.*;
import java.awt.Image;
import java.awt.*;

public class SpecialSpaceShip extends SpaceShip{

	/**
     * This is called once per round by the SpaceWars game driver.
     * defines the ship behaviour, preforms according to the Special behaviour.
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
        	return GameGui.ENEMY_SPACESHIP_IMAGE_SHIELD;
        }
        else
        	return GameGui.ENEMY_SPACESHIP_IMAGE;
    }
}