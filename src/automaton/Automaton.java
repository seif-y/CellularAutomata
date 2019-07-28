package automaton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Automaton extends JPanel implements ActionListener
{
	private static int multi = 10;
	private static int SIZE = 50;
	
	private Grid _grid;
	private Rule _rule;
	private int _height;
	private int _width;
	
	private Timer _timer = new Timer(100, this);
	
	
	/**
	 * Constructor for the Automaton class
	 * @param size The size of the grid, i.e. the number of cells for each side
	 * @param birth The B rule: list of numbers of neighbours that will cause a dead cell to be born
	 * @param survival The S rule: numbers of neighbours that will cause a live cell to survive (not die)
	 */
	public Automaton(int size, int[] birth, int[] survival)
	{
		this.setBackground(Color.RED);
		_grid = new Grid(size, size);
		_rule = new Rule(birth, survival);
		_height = size;
		_width = size;
		
		_timer.start();
	}
	
	

	/**
	 * Goes through each cell, checks its state, and assigns its colour accordingly.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		for (int i = 0; i < _height; i++)
		{
			for (int j = 0; j < _width; j++)
			{
				if (_grid.getState(i,j) == true)
				{
					g.setColor(Color.BLACK);
					g.fillRect(multi*i,multi*j,multi,multi);
				}
				else
				{
					g.setColor(Color.WHITE);
					g.fillRect(multi*i,multi*j,multi,multi);
				}
			}
		}
	}
	
	
	
	/**
	 * This method is executed every time the timer creates an event.
	 * It updates each cell in the grid using the B/S rules that is defined in the constructor
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		for (int i = 0; i < _height; i++)
		{
			for (int j = 0; j < _width; j++)
			{
				int neighbours = _grid.countCellNeighbours(i,j);
				
				int[] list;
				
				boolean cellIsAlive = _grid.getState(i,j);
				
				if (_grid.getState(i,j) == true)
				{
					list = _rule.getS();
				}
				else
				{
					list = _rule.getB();
				}
				
				boolean found = false;
				
				for (int x : list)
				{
					if (neighbours == x)
					{
						found = true;
						break;
					}
				}
				
				if ((cellIsAlive && !found) || (!cellIsAlive && found))
				{
					_grid.updateCell(i,j);
				}
			}
		}
		
		repaint();
		
	}
	
	
	
	/**
	 * Main method: creates a frame to display the cellular automaton grid.
	 */
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Cellular Automaton");
		
		int[] s = {2,3};
		int[] b = {3};
		frame.add(new Automaton(SIZE,b,s));
		frame.setLocationRelativeTo(null);
		frame.setSize(500,500);
		frame.setVisible(true);
	}
}
