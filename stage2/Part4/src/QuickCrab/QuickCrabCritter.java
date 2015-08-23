import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>QuickCrabCritter</code> looks at a limited set of neighbors when it
 * eats and moves. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class QuickCrabCritter extends CrabCritter {
  /**
   * A QuickCrab moves to one of the two locations, randomly selected, that are
   * two spaces to its right or left.
   * @return list of empty locations immediately in two steps to the right and to
   *  the left.
   */
  public ArrayList<Location> getMoveLocations() {
    ArrayList<Location> locs = new ArrayList<Location>();
    int[] dirs = { Location.LEFT, Location.RIGHT };
    getMoveLocationsInDirection(locs,getDirection() + dirs[0]);
    getMoveLocationsInDirection(locs,getDirection() + dirs[1]);

    if (locs.size() == 0) {
      return super.getMoveLocations();
    }
    return locs;
  }

  /**
   * gets the location that is two steps far from the QuickCrab in a given direction.
   * @param locs
   * @param direction
   */
  public void getMoveLocationsInDirection(ArrayList<Location> locs, int direction) {
    Grid gr = getGrid();
    Location currentLoc = getLocation();

    Location neighbor1 = currentLoc.getAdjacentLocation(direction);
    if (gr.isValid(neighbor1) && gr.get(neighbor1) == null) {
      Location neighbor2 = neighbor1.getAdjacentLocation(direction);
      if (gr.isValid(neighbor2) && gr.get(neighbor2) == null) {
        locs.add(neighbor2);
      }
    }
  }
}
