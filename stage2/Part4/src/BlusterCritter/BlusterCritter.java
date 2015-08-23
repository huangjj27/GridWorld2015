import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>BlusterCritter</code> looks at all of the neighbors within two steps
 * of its current location. (For a BlusterCritter not near an edge, this
 * includes 24 locations). <br />
 */
public class BlusterCritter extends Critter {
  private static final int DARKENING_FACTOR = -1;
  private static final int BRIGHTENING_FACTOR = 1;

  private int courage;

  /**
   * constructs a BlustCritter with a courage
   *
   * @param c
   *          the courage needed for a BlustCritter to brighten itself
   */
  public BlusterCritter(int c) {
    courage = c;
  }

  /**
   * looks at all of the neighbors within two steps of a BlustCritter current
   * location.
   */
  public ArrayList<Actor> getActors() {
    ArrayList<Actor> neighbors = new ArrayList<Actor>();
    Location loc = getLocation();
    for (int raw = loc.getRow() - 2; raw <= loc.getRow() + 2; raw++) {
      for (int col = loc.getCol() - 2; col <= loc.getCol() + 2; col++) {
        Location tempLoc = new Location(raw, col);
        if (getGrid().isValid(tempLoc)) {
          Actor tempAct = getGrid().get(tempLoc);
          if (tempAct != null && tempAct != this) {
            neighbors.add(tempAct);
          }
        }
      }
    }

    return neighbors;
  }

  /**
   * searches for the critters nearby. If there are fewer than c critters, the
   * BlusterCritter's color gets brighter (color values increase). If there are
   * c or more critters, the BlusterCritter's color darkens (color values
   * decrease). Here, c is a value that indicates the courage of the critter.
   */
  public void processActors(ArrayList<Actor> actors) {
    int n = 0;
    for (Actor a : actors) {
      if (a instanceof Critter) {
        n++;
      }
    }
    if (n < courage) {
      colorChange(BRIGHTENING_FACTOR);
      return;
    } else {
      colorChange(DARKENING_FACTOR);
      return;
    }
  }

  public int getCourage() {
    return courage;
  }

  private void colorChange(int changeFactor) {
    Color c = getColor();

    int redAfter = c.getRed() + changeFactor;
    int greenAfter = c.getGreen() + changeFactor;
    int blueAfter = c.getBlue() + changeFactor;
    if (redAfter < 0 || redAfter > 255) {
      redAfter -= changeFactor;
    }
    if (greenAfter < 0 || greenAfter > 255) {
      greenAfter -= changeFactor;
    }

    if (blueAfter < 0 || blueAfter > 255) {
      blueAfter -= changeFactor;
    }

    setColor(new Color(redAfter, greenAfter, blueAfter));
  }
}
