package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		Cell c = new Cell(randGen.nextInt(w), randGen.nextInt(h));

		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(c);

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		neighbors = getUnvisitedNeighbors(currentCell);
		for (int i = 0; i < neighbors.size(); i++) {
			System.out.println("N get:" + neighbors.get(i).getX() + " " + neighbors.get(i).getY());
		}
		// C. if has unvisited neighbors,
		if (neighbors.size() > 0) {
			// C1. select one at random.
			Cell r = neighbors.get(randGen.nextInt(neighbors.size()));
			System.out.println("x & y: " + r.getX() + " " + r.getY());
			// C2. push it to the stack
			uncheckedCells.push(r);
			// C3. remove the wall between the two cells
			removeWalls(currentCell, r);
			// C4. make the new cell the current cell and mark it as visited
			currentCell = r;
			// System.out.println("Current cell " + currentCell);
			currentCell.setBeenVisited(true);
			// C5. call the selectNextPath method with the current cell
			System.out.println("      " + currentCell.getX() + " " + currentCell.getY());
			selectNextPath(currentCell);
		} else {
			if (!uncheckedCells.isEmpty()) {
				Cell newC = uncheckedCells.pop();
				currentCell = newC;
				// System.out.println(currentCell.getX());
				selectNextPath(newC);
			}
		}

		// D. if all neighbors are visited

		// D1. if the stack is not empty

		// D1a. pop a cell from the stack

		// D1b. make that the current cell

		// D1c. call the selectNextPath method with the current cell

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getX() == c2.getX()) {
			if (c1.getY() < c2.getY()) {
				// c1 higher than c2
				c1.setSouthWall(false);
				c2.setNorthWall(false);
				//System.out.println("c1 south remove");
			} else if (c1.getY() > c2.getY()) {
				// c1 below c2
				c1.setNorthWall(false);
				c2.setSouthWall(false);
				//System.out.println("c1 north remove");
			}
		} else if (c1.getX() < c2.getX()) {
			// c1 left of c2
			if (c1.getY() == c2.getY()) {
				c1.setEastWall(false);
				c2.setWestWall(false);
				//System.out.println("c1 east remove");
			}
		} else if (c1.getX() > c2.getX()) {
			// c1 right of c2
			if (c1.getY() == c2.getY()) {
				c1.setWestWall(false);
				c2.setEastWall(false);
				//System.out.println("c1 west remove");
			}
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> list = new ArrayList<Cell>();

		if (c.getX() == 0) {
			if (c.getY() == 0) {
				System.out.println("0,0");
				for (int i = c.getX(); i < c.getX() + 2; i++) {
					for (int j = c.getY(); j < c.getY() + 2; j++) {
						// System.out.println(i + " " + j);
						if (maze.cells[i][j].hasBeenVisited() == false) {
							list.add(maze.cells[i][i]);
						}
					}
				}
			} else if (c.getY() == 4) {
				System.out.println("0,4");
				for (int i = c.getX(); i < c.getX() + 2; i++) {
					for (int j = c.getY() - 1; j < c.getY()+1; j++) {
						// System.out.println(i + " " + j);
						if (maze.cells[i][j].hasBeenVisited() == false) {
							// System.out.println("true");
							list.add(maze.cells[i][j]);
						}
					}
				}
			} else {
				System.out.println("0,other");
				for (int i = c.getX(); i < c.getX() + 2; i++) {
					for (int j = c.getY() - 1; j < c.getY() + 2; j++) {
						// System.out.println(i + " " + j);
						if (maze.cells[i][j].hasBeenVisited() == false) {
							list.add(maze.cells[i][j]);
						}
					}
				}
			}
		} else if (c.getX() == 4) {
			if (c.getY() == 0) {
				System.out.println("4,0");
				for (int i = c.getX() - 1; i < c.getX(); i++) {
					for (int j = c.getY(); j < c.getY() + 1; j++) {
						// System.out.println(i + " " + j);
						if (maze.cells[i][j].hasBeenVisited() == false) {
							list.add(maze.cells[i][i]);
						}
					}
				}
			} else if (c.getY() == 4) {
				System.out.println("4,4");
				for (int i = c.getX() - 1; i < c.getX() + 1; i++) {
					for (int j = c.getY() - 1; j < c.getY() + 1; j++) {
						// System.out.println(i + " " + j);
						if (maze.cells[i][j].hasBeenVisited() == false) {
							list.add(maze.cells[i][j]);
						}
					}
				}
			} else {
				System.out.println("4,other");
				for (int i = c.getX() - 1; i < c.getX() + 1; i++) {
					for (int j = c.getY() - 1; j < c.getY() + 2; j++) {
						// System.out.println(i + " " + j);
						if (maze.cells[i][j].hasBeenVisited() == false) {
							list.add(maze.cells[i][j]);
						}
					}
				}
			}
		} else {
			if (c.getY() == 0) {
				System.out.println("other,0");
				for (int i = c.getX() - 1; i < c.getX() + 2; i++) {
					for (int j = c.getY(); j < c.getY() + 2; j++) {
						// System.out.println(i + " " + j);
						if (maze.cells[i][j].hasBeenVisited() == false) {
							list.add(maze.cells[i][i]);
						}
					}
				}
			} else if (c.getY() == 4) {
				System.out.println("other,4");
				for (int i = c.getX() - 1; i < c.getX() + 2; i++) {
					for (int j = c.getY() - 1; j < c.getY() + 1; j++) {
						// System.out.println(i + " " + j);
						if (maze.cells[i][j].hasBeenVisited() == false) {
							list.add(maze.cells[i][i]);
						}
					}
				}
			} else {
				System.out.println("other,other");
				for (int i = c.getX() - 1; i < c.getX() + 2; i++) {
					for (int j = c.getY() - 1; j < c.getY() + 2; j++) {
						// System.out.println(i + " " + j);
						if (maze.cells[i][j].hasBeenVisited() == false) {
							list.add(maze.cells[i][j]);
						}
					}
				}
			}
		}

		return list;
	}
}
