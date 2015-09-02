import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.*;

/**
 * This class runs a world that contains box bugs.
 */
public final class DancingBugRunner {
  /**
   * constructs a new runner instance doing nothings
   */
  private DancingBugRunner() {
  }

  public static void main(String[] args) {
    String str = "1,2,3,4";
    ActorWorld world = new ActorWorld();
    DancingBug alice = new DancingBug(str);
    alice.setColor(Color.ORANGE);
    world.add(new Location(7, 8), alice);
    world.show();
  }
}

