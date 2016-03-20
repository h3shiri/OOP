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
        boolean up = true;
        SpaceShip enemyShip = game.getClosestShipTo(this);
        SpaceShipPhysics enemy_physics = enemyShip.getPhysics();
        double angle =  getPhysics().angleTo(enemy_physics);
        double distance = getPhysics().distanceFrom(enemy_physics);
        /** teleportation known as escape conditions */
        double angleBound = 0.23;
        double distanceBound = 0.25;
        if((angle < angleBound) && (distance < distanceBound)){
            teleport();
        }
        int left = -1;
        int right = 1;
        int direction;
        if(angle >= 0)
            direction = left;
        /** almost behind the ship */
        else if (((angle < 1.57)&&(angle > 1.55)) || ((-1.57 < angle)&&(angle<-1.55))) {
            direction = 0;
        }
        else
            direction = right;
        getPhysics().move(up, direction);

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