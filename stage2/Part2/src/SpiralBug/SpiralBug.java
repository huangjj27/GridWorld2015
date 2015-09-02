import info.gridworld.actor.Bug;

/**
 * A <code>SpiralBug</code> traces out an eight-edge-square of a given size.
 */
public class SpiralBug extends Bug {
  private int steps;
  private int sideLength;

  /**
   * Constructs a box bug that traces an expanding square of a given side length
   *
   * @param length the first edge length
   */
  public SpiralBug(int length) {
    steps = 0;
    sideLength = length;
  }

  /**
   * Moves to the next location of the expanding square. After completes a side,
   * the side length increases.
   */
  public void act() {
    if (steps < sideLength && canMove()) {
      move();
      steps++;
    } else {
      turn();
      turn();
      steps = 0;
      sideLength++;
    }
  }
}

