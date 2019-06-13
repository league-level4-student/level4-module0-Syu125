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
		//System.out.println(c.getX());
		//System.out.println(c.getY());

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
			//System.out.println("got");
		//	System.out.println(neighbors.get(i));
		}
		//System.out.println("hi " + neighbors.size());
		// C. if has unvisited neighbors,
		if (neighbors.size() > 0) {
			// C1. select one at random.
			Cell r = neighbors.get(randGen.nextInt(neighbors.size()));
			// C2. push it to the stack
			uncheckedCells.push(r);
			// C3. remove the wall between the two cells
			removeWalls(currentCell, r);
			// C4. make the new cell the current cell and mark it as visited
			currentCell = r;
			//System.out.println("Current cell " + currentCell);
			currentCell.setBeenVisited(true);
			// C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		} else {
			if (!uncheckedCells.isEmpty()) {
				Cell newC = uncheckedCells.pop();
				currentCell = newC;
				//System.out.println(currentCell.getX());
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
		Boolean adjacent = false;
		for (int i = c1.getX(); i < c1.getX(); i++) {
			for (int j = c1.getY(); j < c2.getY(); j ++) {
				if (c2.getX() == i) {
					if (c2.getY() == j) {
						System.out.println(adjacent);
					}
				}
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
				//System.out.println("0,0");
				for (int i = c.getX(); i < c.getX() + 2; i++) {
					for (int j = c.getY(); j < c.getY() + 2; j++) {
						//System.out.println(i + " " + j);
						if (maze.cells[i][j].hasBeenVisited() == false) {
							list.add(maze.cells[i][i]);
						}
					}
				}
			} else if (c.getY() == 4) {
				//System.out.println("0,4");
				for (int i = c.getX(); i < c.getX() + 2; i++) {
					for (int j = c.getY() - 1; j < c.getY(); j++) {
						//System.out.println(i + " " + j);
						if (maze.cells[i][j].hasBeenVisited() == false) {
							//System.out.println("true");
							list.add(maze.cells[i][j]);
						}
					}
				}
			} else {
				//System.out.println("0,other");
				for (int i = c.getX(); i < c.getX() + 2; i++) {
					for (int j = c.getY() - 1; j < c.getY() + 2; j++) {
						//System.out.println(i + " " + j);
						if (maze.cells[i][j].hasBeenVisited() == false) {
							list.add(maze.cells[i][j]);
						}
					}
				}
			}
		} else if (c.getX() == 4) {
			if (c.getY() == 0) {
				//System.out.println("4,0");
				for (int i = c.getX() - 1; i < c.getX(); i++) {
					for (int j = c.getY(); j < c.getY() + 1; j++) {
						//System.out.println(i + " " + j);
						if (maze.cells[i][j].hasBeenVisited() == false) {
							list.add(maze.cells[i][i]);
						}
					}
				}
			} else if (c.getY() == 4) {
				//System.out.println("4,4");
				for (int i = c.getX() - 1; i < c.getX() + 1; i++) {
					for (int j = c.getY() - 1; j < c.getY() + 1; j++) {
						//System.out.println(i + " " + j);
						if (maze.cells[i][j].hasBeenVisited() == false) {
							list.add(maze.cells[i][j]);
						}
					}
				}
			} else {
				//System.out.println("4,other");
				for (int i = c.getX() - 1; i < c.getX() + 1; i++) {
					for (int j = c.getY() - 1; j < c.getY() + 2; j++) {
						//System.out.println(i + " " + j);
						if (maze.cells[i][j].hasBeenVisited() == false) {
							list.add(maze.cells[i][j]);
						}
					}
				}
			}
		} else {
			if (c.getY() == 0) {
				//System.out.println("other,0");
				for (int i = c.getX() - 1; i < c.getX() + 2; i++) {
					for (int j = c.getY(); j < c.getY() + 2; j++) {
						//System.out.println(i + " " + j);
						if (maze.cells[i][j].hasBeenVisited() == false) {
							list.add(maze.cells[i][i]);
						}
					}
				}
			} else if (c.getY() == 4) {
				//System.out.println("other,4");
				for (int i = c.getX() - 1; i < c.getX() + 2; i++) {
					for (int j = c.getY() - 1; j < c.getY() + 1; j++) {
						//System.out.println(i + " " + j);
						if (maze.cells[i][j].hasBeenVisited() == false) {
							list.add(maze.cells[i][i]);
						}
					}
				}
			} else {
				//System.out.println("other,other");
				for (int i = c.getX() - 1; i < c.getX() + 2; i++) {
					for (int j = c.getY() - 1; j < c.getY() + 2; j++) {
						//System.out.println(i + " " + j);
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
