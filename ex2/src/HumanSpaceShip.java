/** HumanSpaceShip */
import java.awt.Image;
import oop.ex2.*;

public class HumanSpaceShip extends SpaceShip{

    /**
     * This is called once per round by the SpaceWars game driver.
     * Preforms various tasks, depending on the player input.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        GameGUI gui = game.getGUI();
        boolean up = gui.isUpPressed();
        boolean left = gui.isLeftPressed();
        boolean right = gui.isRightPressed();
        boolean fire = gui.isShotPressed();
        boolean tele = gui.isTeleportPressed();
        boolean shield = gui.isShieldsPressed();
        int direction;
        if(right && (!left)){
            direction = -1;
        }
        else if (left && (!right)) {
            direction = 1;
        }
        else
            direction = 0;
        /** we established all the movement parameters and actions */
        if(tele)
            teleport();
        physics.move(up, direction);

        if(shield)
            shieldOn();
        else
            shieldsCondition = false;
        if (fire)
            fire(game);
        if(!shieldsCondition)
            currentEnergyLevel++;

            

    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public Image getImage(){
        if(shieldsCondition)
            return GameGUI.SPACESHIP_IMAGE_SHIELD;
        else
            return GameGUI.SPACESHIP_IMAGE;
    }

}