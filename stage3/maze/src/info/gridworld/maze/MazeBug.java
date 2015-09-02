package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
  public Location next;
  public Location last;
  public boolean isEnd = false;
  public Stack<ArrayList<Location>> crossLocation;
  public Integer stepCount = 0;
  public ArrayList<Location> rightPath;
  boolean hasShown = false;// final message has been shown
  // four direction prediction possibility.
  // four number stand for NORTH, EAST, SOUTH, West.
  public int[] dirPrc = {1, 1, 1, 1};
  private static int rightAngle = 90;

  /**
   * Constructs a box bug that traces a square of a given side length
   */
  public MazeBug() {
    setColor(Color.GREEN);
    last = new Location(0, 0);
    crossLocation = new Stack<ArrayList<Location>>();
    rightPath = new ArrayList<Location>();
  }

  public Stack<ArrayList<Location>> getCross() {
    return crossLocation;
  }

  public String getPredict() {
    return "[" + dirPrc[0] + ", " + dirPrc[1] + ", " + dirPrc[2] + ", "
            + dirPrc[3] + "]";
  }

  /**
   * Moves to the next location of the square.
   */
  public void act() {
    Location locBefore = getLocation();

    // get the fisrt location to avoid the empty stack exception.
    if (stepCount == 0) {
      last = locBefore;
      ArrayList<Location> currentPath = new ArrayList<Location>();
      currentPath.add(last);
      crossLocation.add(currentPath);
    }
    boolean willMove = canMove();
    if (isEnd == true) {

      showPath();
      // to show step count when reach the goal
      if (hasShown == false) {
        String msg = stepCount.toString() + " steps";
        JOptionPane.showMessageDialog(null, msg);
        hasShown = true;
      }
    } else if (willMove) {
      // if the last location is a crosspoint, add a new branch
      if (getValid(locBefore).size() > 1) {
        ArrayList<Location> newBranch = new ArrayList<Location>();
        crossLocation.push(newBranch);
      }
      move();

      // current location push into stack
      ArrayList<Location> currentBranch = crossLocation.pop();
      currentBranch.add(getLocation());
      crossLocation.push(currentBranch);
      // a new Branch is created. change Prediction.
      if (currentBranch.size() == 1) {
        increasePredict(locBefore, getLocation());
      }

      // increase step count when move
      stepCount++;
    } else {
      // if the branch is a dead end, pops out the current location, and Drops a
      // flower to show that the location has been accessed to.
      ArrayList<Location> currentBranch = crossLocation.pop();
      currentBranch.remove(currentBranch.size() - 1);
      // if the branch is not empty, it must push in.
      if (currentBranch.size() > 0) {
        crossLocation.push(currentBranch);
      }
      goBack();

      // a Branch is deleted. change Prediction.
      if (currentBranch.size() == 0) {
        decreasePredict(getLocation(), locBefore);
      }
      stepCount++;
    }
  }

  private void showPath() {
    Grid gr = getGrid();
    while (!crossLocation.isEmpty()) {
      ArrayList<Location> currentBranch = crossLocation.pop();
      if (!currentBranch.isEmpty()) {
        for (Location loc : currentBranch) {
          rightPath.add(loc);
        }
      }
    }

    for (Location loc : rightPath) {
      Actor act = (Actor) gr.get(loc);
      act.setColor(Color.GREEN);
    }
  }

  /**
   * Find all positions that can be move to.
   *
   * @param loc the location to detect.
   * @return List of positions. only the empty location and the destination will
   * be added.
   */
  public ArrayList<Location> getValid(Location loc) {
    Grid<Actor> gr = getGrid();
    if (gr == null) {
      return null;
    }
    ArrayList<Location> valid = new ArrayList<Location>();
    int directions[] = {Location.NORTH, Location.EAST, Location.SOUTH,
            Location.WEST};

    for (int d : directions) {
      Location neighbor = loc.getAdjacentLocation(d);
      if (gr.isValid(neighbor)) {
        Actor neighborActor = gr.get(neighbor);
        if (isDestination(neighbor)) {
          // if the destination is find, move to it
          valid.removeAll(valid);
          valid.add(neighbor);
          return valid;
        } else if (neighborActor == null) {
          valid.add(neighbor);
        }
      }
    }

    return valid;
  }

  /**
   * Tests whether this bug can move forward into a location that is empty or
   * contains a flower.
   *
   * @return true if this bug can move.
   */
  public boolean canMove() {
    ArrayList<Location> nexts = getValid(getLocation());
    if (nexts.size() > 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Moves the bug forward, putting a flower into the location it previously
   * occupied.
   */
  public void move() {
    Grid<Actor> gr = getGrid();
    if (gr == null) {
      return;
    }

    // get the next location to move to randomly.
    Location loc = getLocation();
    ArrayList<Location> nexts = getValid(loc);
    int nextNum = getNextNum(nexts);
    next = nexts.get(nextNum);

    if (gr.isValid(next)) {
      setDirection(getLocation().getDirectionToward(next));
      if (isDestination(next)) {
        isEnd = true;
      }
      moveTo(next);
    } else {
      removeSelfFromGrid();
    }
    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, loc);
  }

  private int getNextNum(ArrayList<Location> nexts) {
    Random rand = new Random();
    int sum0 = dirPrc[0];
    int sum1 = sum0 + dirPrc[1];
    int sum2 = sum1 + dirPrc[2];
    int sum3 = sum2 + dirPrc[3];

    int temp = rand.nextInt(sum3);
    if (temp < sum0) {
      temp = 0;
    } else if (temp < sum1) {
      temp = 1;
    } else if (temp < sum2) {
      temp = 2;
    } else {
      temp = 3;
    }
    while (temp > 0 && temp >= nexts.size()) {
      temp--;
    }

    return temp;
  }

  private void goBack() {
    Grid<Actor> gr = getGrid();
    Location loc = getLocation();
    ArrayList<Location> currentBranch = crossLocation.peek();
    Location lastLoc = currentBranch.get(currentBranch.size() - 1);

    setDirection(getLocation().getDirectionToward(lastLoc));
    moveTo(lastLoc);

    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, loc);
  }

  private boolean isDestination(Location loc) {
    Actor locActor = getGrid().get(loc);
    return (locActor instanceof Rock && locActor.getColor().equals(Color.RED));
  }

  private void decreasePredict(Location locBefore, Location locNew) {
    int dir = (int) ((locBefore.getDirectionToward(locNew)) / rightAngle);
    if (dirPrc[dir] > 1) {
      dirPrc[dir]--;
    }
  }

  private void increasePredict(Location locBefore, Location locNew) {
    int dir = (int) ((locBefore.getDirectionToward(locNew)) / rightAngle);
    dirPrc[dir]++;
  }

}
