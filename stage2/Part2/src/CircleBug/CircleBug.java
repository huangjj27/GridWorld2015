import info.gridworld.actor.Bug;

/**
 * A <code>CircleBug</code> traces out an eight-edge-square of a given size.
 */
public class CircleBug extends Bug {
  private int steps;
  private int sideLength;

  /**
   * Constructs a circle bug that traces a circle-like shape of a given side length
   *
   * @param length the side length
   */
  public CircleBug(int length) {
    steps = 0;
    sideLength = length;
  }

  /**
   * Moves to the next location of the trace.
   */
  public void act() {
    if (steps < sideLength && canMove()) {
      move();
      steps++;
    } else {
      turn();
      steps = 0;
    }
  }
}

