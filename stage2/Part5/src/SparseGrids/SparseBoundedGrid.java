import info.gridworld.grid.*;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * <code>SparseBoundedGrid</code> contains the methods that are common to grid
 * implementations. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E> {
  private SparseGridNode[] occupantArray;
  private int rows;
  private int columns;

  /**
   * Constructs an empty bounded grid with the given dimensions. (Precondition:
   * <code>rowNum > 0</code> and <code>colNum > 0</code>.)
   * 
   * @param rowNum
   *          number of rows in BoundedGrid
   * @param colNum
   *          number of columns in BoundedGrid
   */
  public SparseBoundedGrid(int rowNum, int colNum) {
    if (rowNum <= 0) {
      throw new IllegalArgumentException("rowNum <= 0");
    }
    if (colNum <= 0) {
      throw new IllegalArgumentException("colNum <= 0");
    }

    rows = rowNum;
    columns = colNum;
    occupantArray = new SparseGridNode[rowNum];
  }

  public int getNumRows() {
    return rows;
  }

  public int getNumCols() {
    return columns;
  }

  public boolean isValid(Location loc) {
    boolean rowValid = (0 <= loc.getRow() && loc.getRow() < rows);
    boolean colValid = (0 <= loc.getCol() && loc.getCol() < columns);
    return rowValid && colValid;
  }

  public E put(Location loc, E obj) {
    if (!isValid(loc)) {
      throw new IllegalArgumentException("Location " + loc + " is not valid");
    }
    if (obj == null) {
      throw new NullPointerException("obj == null");
    }

    // Add the object to the grid. we put the new occupant to the front of a
    // row list.
    E oldOccupant = remove(loc);
    SparseGridNode oldRowHead = occupantArray[loc.getRow()];
    SparseGridNode newRowHead = new SparseGridNode(obj, loc.getCol(), null,
        oldRowHead);
    if (oldRowHead != null) {
      oldRowHead.setPre(newRowHead);
    }
    occupantArray[loc.getRow()] = newRowHead;
    return oldOccupant;
  }

  public E remove(Location loc) {
    if (!isValid(loc)) {
      throw new IllegalArgumentException("Location " + loc + " is not valid");
    }

    SparseGridNode locNode = getNode(loc);
    // Remove the object from the grid. if no found return null
    E toRemove = get(locNode);
    if (toRemove == null) {
      return null;
    }
    // maintain the linked row

    if (locNode != null) {
      SparseGridNode preNode = locNode.getPre();
      SparseGridNode nextNode = locNode.getNext();
      if (preNode != null) {
        preNode.setNext(nextNode);
      } else if (occupantArray[loc.getRow()] != null) {
        // the preNode is null indicates that the locNode is the Head of the
        // row.
        // the head changes!
        occupantArray[loc.getRow()] = nextNode;
      }
      if (nextNode != null) {
        nextNode.setPre(preNode);
      }
    }

    return toRemove;
  }

  public E get(Location loc) {
    SparseGridNode node = getNode(loc);
    if (node != null) {
      return (E) node.getOccupant();
    } else {
      return null;
    }
  }

  public ArrayList<Location> getOccupiedLocations() {
    ArrayList<Location> occupied = new ArrayList<Location>();

    // traverses all occupied locations.
    for (int row = 0; row < rows; row++) {
      SparseGridNode rowListNode = occupantArray[row];
      while (rowListNode != null) {
        // a location appearing in the list indicates that it is valid
        // and no necessity to check. it will be added to the return list.
        Location loc = new Location(row, rowListNode.getCol());
        occupied.add(loc);

        // Traverse the next location in the row.
        rowListNode = rowListNode.getNext();
      }
    }
    return occupied;
  }

  /**
   * 
   * @param loc
   * @return
   */
  private SparseGridNode getNode(Location loc) {
    SparseGridNode rowListNode = occupantArray[loc.getRow()];
    while (rowListNode != null) {
      if (rowListNode.getCol() == loc.getCol()) {
        // force cast to the E type object.
        return rowListNode;
      }

      // Traverse the next node in the row list
      rowListNode = rowListNode.getNext();
    }
    return null;
  }

  private E get(SparseGridNode node) {
    if (node != null) {
      return (E) node.getOccupant();
    } else {
      return null;
    }
  }
}
