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
        rechargeGuns();

        SpaceShip enemyShip = game.getClosestShipTo(this);
        SpaceShipPhysics enemy_physics = enemyShip.getPhysics();
        double distance = getPhysics().distanceFrom(enemy_physics);
        double distanceBound = 0.25;
        if(distance < distanceBound)
            shieldOn();
        else
            shieldsCondition = false;
        fire(game);
        double terminationBound = 0.14;
        if(distance < terminationBound){
            getPhysics().move(false, 0);
            mindControl(enemyShip);
        }
        else
            getPhysics().move(false, 1);
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
    /** fully charging the energy level */
    private void superCharge(){
        currentEnergyLevel = maximumEnergy;
    }
    /** ending any coldownPeriod for the guns */
    private void setGuns(){
        tempShotCounter = 0;
    }
    /** A useful tool to destroy the nearest enemy ship */
    private void mindControl(SpaceShip enemy){
        enemy.healthLevel = 0;
    }
}