import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class JumperRunner {

	/**
	 * Construct a jumperRunner.
	 */
    private JumperRunner() {}

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        alice.setColor(Color.ORANGE);
        world.add(new Location(7, 8), alice);
        world.add(new Bug());
        world.add(new Rock());
        world.show();
    }
}
