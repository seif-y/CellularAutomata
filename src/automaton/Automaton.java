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

	private Grid _grid;
	private Rule _rule;
	private int _height;
	private int _width;
	
	private Color liveCellColor = Color.RED;
	
	private Color deadCellColor = Color.BLACK;
	
	private Timer _timer = new Timer(100, this);
	
	
	/**
	 * Constructor for the Automaton class
	 * @param size The size of the grid, i.e. the number of cells for each side
	 * @param birth The B rule: list of numbers of neighbours that will cause a dead cell to be born
	 * @param survival The S rule: numbers of neighbours that will cause a live cell to survive (not die)
	 */
	public Automaton(int size, int liveCellPct, int[] birth, int[] survival)
	{
		this.setBackground(Color.RED);
		_grid = new Grid(size, size, liveCellPct);
		_rule = new Rule(birth, survival);
		_height = size;
		_width = size;
	}
	
	

	/**
	 * Toggles the timer on or off depending on its current state.
	 */
	public void toggleTimer()
	{
		if (_timer.isRunning())
		{
			_timer.stop();
		}
		else
		{
			_timer.start();
		}
	}
	
	
	
	/**
	 * Goes through each cell, checks its state, and assigns its colour accordingly.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		int x = this.getWidth() / _width;
		int y = this.getHeight() / _height;
		
		for (int i = 0; i < _height; i++)
		{
			for (int j = 0; j < _width; j++)
			{
				if (_grid.getState(i,j) == true)
				{
					g.setColor(liveCellColor);
					g.fillRect(x*i,y*j,x,y);
				}
				else
				{
					g.setColor(deadCellColor);
					g.fillRect(x*i,y*j,x,y);
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
	
	
	public void newAutomaton(int size, int liveCellPct, int[] b, int[] s)
	{
		_grid = new Grid(size, size, liveCellPct);
		_rule = new Rule(b, s);
		_height = size;
		_width = size;
	}
	
	
	public void setTimerDelay(int ms)
	{
		_timer.setDelay(ms);
	}
	
	
	/*
	 * Main method: creates a frame to display the cellular automaton grid.
	 
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Cellular Automaton");
		
		int[] s = {1,2,3,4,5};
		int[] b = {3};
		frame.add(new Automaton(SIZE,b,s));
		frame.setLocationRelativeTo(null);
		frame.setSize(500,500);
		frame.setVisible(true);
	}*/
}
