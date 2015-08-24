import info.gridworld.grid.*;

import java.util.ArrayList;

/**
 * A <code>UnboundedGrid2</code> is a rectangular grid with a infinite number of
 * rows and columns. <br />
 */
public class UnboundedGrid2<E> extends AbstractGrid<E> {
  private Object[][] occupantArray; // the array storing the grid elements
  private int dimension; // current dimension of the grid

  /**
   * Constructs an empty unbounded grid with the dimension of 16
   */
  public UnboundedGrid2() {
    dimension = 16;
    occupantArray = new Object[dimension][dimension];
  }

  public int getNumRows() {
    return -1;
  }

  public int getNumCols() {
    return -1;
  }

  public boolean isValid(Location loc) {
    return loc.getRow() >= 0 && loc.getCol() >= 0;
  }

  public ArrayList<Location> getOccupiedLocations() {
    ArrayList<Location> occupied = new ArrayList<Location>();

    // Look at all grid locations.
    for (int r = 0; r < dimension; r++) {
      for (int c = 0; c < dimension; c++) {
        // If there's an object at this location, put it in the array.
        Location loc = new Location(r, c);
        if (get(loc) != null)
          occupied.add(loc);
      }
    }

    return occupied;
  }

  public E get(Location loc) {
    if (!isValid(loc))
      throw new IllegalArgumentException("Location " + loc + " is not valid");
    // return null if a location is valid, but not in the array
    if (loc.getRow() >= dimension || loc.getCol() >= dimension)
      return null;
    return (E) occupantArray[loc.getRow()][loc.getCol()];
  }

  public E put(Location loc, E obj) {
    if (!isValid(loc))
      throw new IllegalArgumentException("Location " + loc + " is not valid");
    if (obj == null)
      throw new NullPointerException("obj == null");

    if (loc.getRow() >= dimension || loc.getCol() >= dimension) {
      resize(loc);
    }

    // Add the object to the grid.
    E oldOccupant = get(loc);
    occupantArray[loc.getRow()][loc.getCol()] = obj;
    return oldOccupant;
  }

  public E remove(Location loc) {
    if (!isValid(loc)) {
      throw new IllegalArgumentException("Location " + loc + " is not valid");
    }

    // if location is valid and not in array, return null
    if (loc.getRow() >= dimension || loc.getCol() >= dimension) {
      return null;
    }
    // Remove the object from the grid.
    E r = get(loc);
    occupantArray[loc.getRow()][loc.getCol()] = null;
    return r;
  }

  private void resize(Location loc) {
    // double the size until it is greater than needed
    int size = dimension;
    while (loc.getRow() >= size || loc.getCol() >= size) {
      size *= 2;
    }

    // create a new array
    Object[][] temp = new Object[size][size];

    // copy over the old contents
    for (int r = 0; r < dimension; r++)
      for (int c = 0; c < dimension; c++)
        temp[r][c] = occupantArray[r][c];

    // assign occupantArray the new array and update dimension
    occupantArray = temp;
    dimension = size;
  }
}
