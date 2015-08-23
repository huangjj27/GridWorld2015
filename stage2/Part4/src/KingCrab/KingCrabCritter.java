import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>KingCrabCritter</code> looks at a limited set of neighbors when it
 * eats and moves. Meanwhile, it pushes the actors in the front of it. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class KingCrabCritter extends CrabCritter {
  public void processActors(ArrayList<Actor> actors) {
    Grid gr = getGrid();
    int direction = getDirection();
    for (Actor a : actors) {
      Location temp = a.getLocation().getAdjacentLocation(direction + Location.AHEAD);
      if (gr.isValid(temp)) {
        a.moveTo(temp);
      } else {
        a.removeSelfFromGrid();
      }
    }
  }
}
