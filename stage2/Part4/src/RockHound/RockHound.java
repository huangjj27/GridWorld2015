import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;

import java.util.ArrayList;

/**
 * A <code>RockHound</code> takes on the color of neighboring actors as it moves
 * through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class RockHound extends Critter {
  /**
   * If the neighbors have rocks, them will be removed
   */
  public void processActors(ArrayList<Actor> actors) {
    for (Actor a : actors) {
      if (!(a instanceof Critter)) {
        a.removeSelfFromGrid();
      }
    }
  }
}
