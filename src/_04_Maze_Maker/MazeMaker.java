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
		// C. if has unvisited neighbors,
		if (neighbors.size() > 0) {
			// C1. select one at random.
			Cell r = neighbors.get(randGen.nextInt(neighbors.size() - 1));
			// C2. push it to the stack
			uncheckedCells.push(r);
			// C3. remove the wall between the two cells
			removeWalls(currentCell, r);
			// C4. make the new cell the current cell and mark it as visited
			currentCell = r;
			currentCell.setBeenVisited(true);
			// C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}else {
			if(!uncheckedCells.isEmpty()) {
				Cell newC = uncheckedCells.pop();
				currentCell = newC;
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
		for(int i = c1.getX()-100; i < c1.getX()+100;i+=100) {
			for(int j = c1.getY()-100; j < c2.getY()+100; j+=100) {
				if(c2.getX() == i) {
					if(c2.getY() == i) {
						adjacent = true;
					}
				}
			}
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell>list = new ArrayList<Cell>();
		if(c.getX()<5 && c.getY()<5 && c.getX()>0 && c.getY()>0) {
		for(int i = c.getX()-1; i < c.getX()+1; i++) {
			for (int j = c.getY()-1; j < c.getY()+1; j++) {
				if(maze.cells[i][j].hasBeenVisited() == true) {
					list.add(maze.cells[i][j]);
				}
			}
		}
		}
		return list;
	}
}
