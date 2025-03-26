/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells

        // Create an Array List to track the solution
        ArrayList<MazeCell> arr = new ArrayList<MazeCell>();
        // Start at the end
        MazeCell current = maze.getEndCell();
        //Create a stack to reverse the path
        Stack<MazeCell> stack = new Stack<MazeCell>();
        // While we have not finished the maze yet
        while (!current.equals(maze.getStartCell())) {
            // Add step to the stack
            stack.push(current);
            // Keep on going up
            current = current.getParent();
        }
        // Push the starting cell as well
        stack.push(current);
        // Reverse the path
        while (!stack.empty()) {
            arr.add(stack.pop());
        }
        // Return the solution
        return arr;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST

        // Create place to store explored cells
        ArrayList<MazeCell> explored = new ArrayList<MazeCell>();

        // Create stack to implement DFS
        Stack<MazeCell> toVisit = new Stack<MazeCell>();

        // Start at the starting point
        MazeCell start = maze.getStartCell();
        explored.add(start);
        toVisit.push(start);

        // Go through the maze
        while (!toVisit.empty()) {
            // Find current cell
            MazeCell current = toVisit.pop();

            // If the current cell is at the end, we are done and can return the solution
            if (current.equals(maze.getEndCell())) {
                return getSolution();
            }

            // Get row and col of current cell
            int row = current.getRow();
            int col = current.getCol();

            // Find all neighbors of cell
            ArrayList<MazeCell> neighbors = new ArrayList<MazeCell>();
            neighbors.add(maze.getCell(row + 1, col)); // North
            neighbors.add(maze.getCell(row, col + 1)); // East
            neighbors.add(maze.getCell(row - 1, col)); // South
            neighbors.add(maze.getCell(row, col - 1)); // West

            // Go through all neighbors
            for (MazeCell neighbor: neighbors) {
                // If it is a valid cell and has not been explored before
                if (neighbor != null && maze.isValidCell(neighbor.getRow(), neighbor.getCol())) {
                    // Add neighbor to explored, toVisit
                    toVisit.push(neighbor);
                    explored.add(neighbor);
                    // Make sure its parent cell is accurate
                    neighbor.setParent(current);
                }
            }
        }
        // If no solution is found return null
        return null;
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        return null;
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
