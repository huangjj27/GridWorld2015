import info.gridworld.actor.Bug;

/**
 * A <code>DancingBug</code> traces as if a bug is dancing.
 */
public class DancingBug extends Bug {
  private int steps;
  private int sideLength;
  private int counter;
  private int[] array;

  /**
   * Constructs a dancing bug that turns afther a certain movement of a few step.
   *
   * @param arrayStr a string contain serval numbers that indicate how many times
   *                 should the bug turns after a movement. The number of steps of a movement is
   *                 also defined by the number of numbers the arrayStr contains.
   */
  public DancingBug(String arrayStr) {
    String[] turnTimes = arrayStr.split(",");
    steps = 0;
    counter = 0;
    sideLength = turnTimes.length;
    array = new int[sideLength];

    for (int i = 0; i < turnTimes.length; i++) {
      array[i] = Integer.valueOf(turnTimes[i]).intValue();
    }
  }

  /**
   * Moves to the next location of the trace. steps of a sideLength is a movement,
   * after a movement the bug turns times that are defined by array.
   */
  public void act() {
    if (steps < sideLength && canMove()) {
      move();
      steps++;
    } else {
      makeTurn(array[counter]);
      counter++;
      if (counter == sideLength) {
        counter = 0;
      }
      steps = 0;
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
