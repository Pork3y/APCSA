package graphicsTest;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.util.Scanner;

public class MapDataDrawer {
	private int[][] grid;
  public MapDataDrawer(String filename, int rows, int cols) {
    grid = new int[rows][cols];
    Scanner data;
    try{
      data = new Scanner(new File(filename));
    } catch(FileNotFoundException e){
      System.out.println(e);
      return;
    }
    for(int row = 0; row < rows; row++){
      for(int col = 0; col < cols; col++){
        grid[row][col] = data.nextInt();
      }
    }
    data.close();
	}

  /**
   * @return the min value in the entire grid
   */
  public int findMinValue() {
		int min = Integer.MAX_VALUE;
    for(int row = 0; row < grid.length; row++){
      for(int col = 0; col < grid[0].length; col++){
        min = Math.min(min, grid[row][col]);
      }
    }
    return min;
  }

  /**
   * @return the max value in the entire grid
   */
	public int findMaxValue() {
		int max = Integer.MIN_VALUE;
    for(int row = 0; row < grid.length; row++){
      for(int col = 0; col < grid[0].length; col++){
        max = Math.max(max, grid[row][col]);
      }
    }
    return max;
	}
	
	/**
   * @param col the column of the grid to check
   * @return the index of the row with the lowest value in the given col for the grid
   */
	public  int indexOfMinInCol(int col) {
    int ind = 0;
    for(int row = 0; row < grid.length; row++){
      if(grid[row][col] < grid[ind][col]){
        ind = row;
      }
    }
    return ind;
	}
	
	/**
   * Draws the grid using the given Graphics object.
   * Colors should be grayscale values 0-255, scaled based on min/max values in grid
   */
   
      //g.setColor(new Color(r, g, b))
      //g.fillRect(x, y, 1, 1)

  public void drawMap(Graphics g) {
    int minValue = findMinValue();
    int maxValue = findMaxValue();
    for(int row = 0; row < grid.length; row++){
      for(int col = 0; col < grid[0].length; col++){
        int value = (int) Math.round((grid[row][col] - minValue) * 255.0 / (maxValue - minValue));
        g.setColor(new Color(value, value, value));
        g.fillRect(col, row, 1, 1);
      }
    }
	}

  /**
   * Find a path from West-to-East starting at given row.
   * Choose a forward step out of 3 possible forward locations, using greedy method described in assignment.
   * @return the total change in elevation traveled from West-to-East
   */

  public int drawLowestElevPath(Graphics g, int row) {
    int totalChange = 0;
    for(int col = 0; col < grid[0].length - 1; col++){
      int minChange = Integer.MAX_VALUE;
      int minNext = 0;
      int up = 0;
      int down = 0;
      for(int next = (row == 0 ? 0 : -1); next <= (row == 479 ? 0 : 1); next++){
        int nextChange = (int) Math.abs(grid[row + next][col + 1] - grid[row][col]);
        if(nextChange < minChange){
          minChange = nextChange;
          minNext = next;
        }
        if(next == 1) up = nextChange;
        if(next == -1) down = nextChange;
      }
      if(up == down && (int) Math.abs(minNext) == 1) minNext *= Math.random() > 0.5 ? 1 : -1;
      totalChange += minChange;
      row += minNext;
      g.fillRect(col + 1, row, 1, 1);
    }
    return totalChange;
  }
	
	/**
   * @return the index of the starting row for the lowest-elevation-change path in the entire grid.
   */
  public int indexOfLowestElevPath(Graphics g) {
    int minTotal = Integer.MAX_VALUE;
    int minInd = 0;
    for(int row =  0; row < grid.length; row++){
      int change = drawLowestElevPath(g, row);
      if(change < minTotal){
        minTotal = change;
        minInd = row;
      }
    }
    return minInd;
  }
}