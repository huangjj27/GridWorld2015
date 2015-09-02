import info.gridworld.actor.Bug;

/**
 * A <code>ZBug</code> traces the shape of "z" only once.
 */
public class ZBug extends Bug {
  private int steps;
  private int sideLength;
  private boolean finish;
  private boolean start;
  private boolean turn1;
  private boolean turn2;

  /**
   * Constructs a box bug that traces a shape of "z".
   *
   * @param length the length of each edge of a "z".
   */
  public ZBug(int length) {
    steps = 0;
    sideLength = length;
    finish = false;
    start = false;
    turn1 = false;
    turn2 = false;
  }

  /**
   * Moves to the next location of the "z". If a bug can't move, it will
   * keep trying to finish the "z" it is tracing, even it will never finish it.
   */
  public void act() {
    if (!finish) {
      if (!start) {
        makeTurn(2);
        start = true;
      }
      if (steps < sideLength && canMove()) {
        move();
        steps++;
      } else {
        if (!canMove()) {
          finish = true;
        } else if (!turn1) {
          makeTurn(3);
          turn1 = true;
        } else if (!turn2) {
          makeTurn(5);
          turn2 = true;
        } else {
          finish = true;
        }
        steps = 0;
      }
    }
  }

  /**
   * makes the bug turns.
   *
   * @param times indicates the angle a bug should turns.
   *              each time means a 45 degree clockwise.
   */
  public void makeTurn(int times) {
    for (int i = times; i > 0; i--) {
      turn();
    }
  }
}

