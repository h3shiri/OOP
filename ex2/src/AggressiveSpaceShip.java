import oop.ex2.*;
import java.awt.Image;
import java.awt.*;

public class AggressiveSpaceShip extends SpaceShip{

	/**
     * This is called once per round by the SpaceWars game driver.
     * defines the ship behaviour, preforms according to the Aggressive behaviour.
     * @parm game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        rechargeGuns();
        boolean up = true;
        SpaceShip enemyShip = game.getClosestShipTo(this);
        SpaceShipPhysics enemy_physics = enemyShip.getPhysics();
        double angle =  getPhysics().angleTo(enemy_physics);
        double distance = getPhysics().distanceFrom(enemy_physics);
        int direction;
        int stright = 0;
        int left = -1;
        int right = 1;
        if((angle < 0.02)&&(angle > -0.02)){
            direction = 0;
        }
        else if (angle >= 0.02) {
            direction = 1;
        }
        else
            direction = -1;
        getPhysics().move(up, direction);
        double distanceBound = 0.21;
        if (distance <= distanceBound)
            fire(game);
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