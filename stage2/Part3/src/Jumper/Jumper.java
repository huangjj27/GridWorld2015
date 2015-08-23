import java.awt.Color;

import info.gridworld.actor.Actor;

import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

public class Jumper extends Actor {
    
	/**
     * Construct a jumper.
     */
    public Jumper() {
        setColor(Color.RED);
    }
    
    /**
     * Construct a jumper and set the color of jumper.
     */
    public Jumper(Color jumperColor) {
        setColor(jumperColor);
    }
    
    /**
     * Turns the bug 45 degrees to the right without changing its location.
     */
    public void turn() {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
    
    /**
     * A Jumper acts by getting a list of other actors, processing that list,
     * firstly to check there is any obstacle in the location, then getting locations to move to
     */
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location next1 = next.getAdjacentLocation(getDirection());
        if (gr.isValid(next1)) {
            moveTo(next1);
        } else {
            removeSelfFromGrid();
        }
    }
    
    /**
     * Tests whether this jumper can move forward into a location that is empty or
     * contains a flower.
     * @return true if this jumper can move.
     */
    public boolean canMove() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location next1 = next.getAdjacentLocation(getDirection());
        if (!gr.isValid(next1)) {
            return false;
        }
        Actor neighbor = gr.get(next1);
        return (neighbor == null) || (neighbor instanceof Flower);
    }
    
    /**
     * Moves to the next location of the square.
     */
    public void act() {
        if (canMove()) {
            move();
        } else {
            turn();
            
        }
    }

}
