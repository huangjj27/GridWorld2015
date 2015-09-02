import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains bluster critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class BlusterRunner {
  public BlusterRunner() {
  }

  public static void main(String[] args) {
    ActorWorld world = new ActorWorld();
    for (int raw = 0; raw < 5; raw++) {
      for (int col = 0; col < 4; col++) {
        world.add(new Location(raw, col), new Rock());
      }
    }
    for (int raw = 1; raw < 4; raw++) {
      for (int col = 1; col < 3; col++) {
        world.add(new Location(raw, col), new BlusterCritter(2 * raw + col));
      }
    }
    world.show();
  }
}
