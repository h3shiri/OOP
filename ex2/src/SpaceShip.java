import java.awt.Image;
import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game. 
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 *  
 * @author oop
 */
public abstract class SpaceShip{
    /** data members */
    protected final int initialHealthLevel = 22;
    protected final int lowerHealthBound = 0;
    protected final int initialMaximumEnergy = 210;
    protected final int initialEnergyLevel = 190;
    protected SpaceShipPhysics physics;
    protected int maximumEnergy;
    protected int currentEnergyLevel;
    protected int healthLevel;
    protected boolean shieldsCondition;
    protected int canonCollingPeriod = 8;
    protected int tempShotCounter = 0;

    protected SpaceShip(){
        healthLevel = initialHealthLevel;
        this.physics = new SpaceShipPhysics();
        maximumEnergy = initialMaximumEnergy;
        currentEnergyLevel = initialEnergyLevel; 
        shieldsCondition = false;
    }

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public abstract void doAction(SpaceWars game);

    /**
     * This method is called every time a collision with this ship occurs
     */
    public void collidedWithAnotherShip(){
        int energySurgeFromBashing = 18;
        if(shieldsCondition){
            currentEnergyLevel += energySurgeFromBashing;
            maximumEnergy += energySurgeFromBashing;
        }
        else
            healthLevel--;
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset(){
        // TODO: Rest any other counter u might use in your implementation.
        physics = new SpaceShipPhysics();
        shieldsCondition = false;
        healthLevel = initialHealthLevel;
        maximumEnergy = initialMaximumEnergy;
        currentEnergyLevel = initialEnergyLevel;

    }

    /**
     * Checks if this ship is dead.
     *
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead(){
        if(healthLevel <= lowerHealthBound){
            return true;
        }
        else
            return false;
    }

    /**
     * Gets the physics object that controls this ship.
     *
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics(){
        return physics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit(){
        int healthDamage = 1;
        int maximalEnergyDamage = 10;
        if(!shieldsCondition){
            healthLevel -= healthDamage;
            maximumEnergy -= maximalEnergyDamage;
            if (currentEnergyLevel > maximumEnergy)
                currentEnergyLevel = maximumEnergy;
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public abstract Image getImage();

    /**
     * Attempts to fire a shot.
     *
     * @param game the game object.
     */
    public void fire(SpaceWars game){
        int weaponsCost = 19;
        if ((currentEnergyLevel > weaponsCost) && (tempShotCounter == 0)) {
            game.addShot(getPhysics());
            currentEnergyLevel -= weaponsCost;
            tempShotCounter++;
        }
    }
    /**
     * re-charging the guns so the re-coil would only lasts 7 turns.
     */
    protected void rechargeGuns(){
        if(tempShotCounter != 0)
            tempShotCounter = ((tempShotCounter+1) % canonCollingPeriod);
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn(){
        // TODO: check the cost of turning on
        int costPerRound = 3;
        if (currentEnergyLevel > costPerRound) {
            shieldsCondition = true;
            currentEnergyLevel -= costPerRound;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport(){
//        TODO: fix teleportation..issues.
        int costOfTele = 140;
        if (currentEnergyLevel > costOfTele){
            this.physics = new SpaceShipPhysics();
            currentEnergyLevel -= costOfTele;
        }
    }
   
}
