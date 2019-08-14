package automaton;

import java.util.Random;

public class Grid
{

	private boolean[][] _grid;
	private int _height;
	private int _width;
	
	public Grid(int height, int width, int liveCellPct)
	{
		_grid = new boolean[height][width];
		_height = height;
		_width = width;

		Random rand = new Random();
		double numLiveCells = ((double)liveCellPct/100) * height*width;

		for (int x = 0; x < (int)numLiveCells; x++)
		{
			int i = rand.nextInt(height);
			int j = rand.nextInt(width);
			_grid[i][j] = true;
		}
	}
	
	public void updateCell(int row, int col)
	{
		if (_grid[row][col] == true)
		{
			_grid[row][col] = false;
		}
		else
		{
			_grid[row][col] = true;
		}
	}
	
	public int countCellNeighbours(int row, int col)
	{
		int neighbours = 0;
		
		for (int i = row - 1; i <= row + 1; i++)
		{
			if ((i >= 0) && (i < _height))
			{
				for (int j = col - 1; j <= col + 1; j++)
				{
					if ((j >= 0) && (j < _width))
					{
						if ((_grid[i][j] == true) && !((i == row) && (j == col))) { neighbours++; }
					}
				}
			}
		}
		
		return neighbours;
	}
	
	public boolean getState(int row, int col)
	{
		return _grid[row][col];
	}
	
	public Grid duplicate()
	{
		Grid newGrid = new Grid(_height, _width, 0);
		newGrid._grid = this._grid;
		return newGrid;
	}
}
