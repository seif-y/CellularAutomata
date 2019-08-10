package mainApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import automaton.Automaton;
import form.AutomatonMenu;

public class CellularAutomaton extends JPanel
{

	private static final long serialVersionUID = 1L;
	
	private static JFrame _frame;
	
	private Automaton _automaton;
	
	private JButton _newAutomaton;
	private JButton _toggleAutomaton;
	
	private CellularAutomaton()
	{
		setLayout(new BorderLayout());
		
		_newAutomaton = new JButton("NEW");
		_toggleAutomaton = new JButton("START/STOP");
		setEventHandlers();
		
		JPanel infoPanel = new JPanel();
		infoPanel.add(_newAutomaton);
		infoPanel.add(_toggleAutomaton);
		infoPanel.setPreferredSize(new Dimension(500,1000));
		add(infoPanel, BorderLayout.WEST);
		
		int[] b = {3};
		int[] s = {2,3};
		_automaton = new Automaton(250, 5, b, s);
		_automaton.setPreferredSize(new Dimension(1000,1000));
		add(_automaton, BorderLayout.CENTER);
		
	}
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createGUI();
			}
		});
	}
	
	private static void createGUI()
	{
	    try
	    { 
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
	    }
	    catch(Exception ignored) {}
		    
		_frame = new JFrame("Cellular Automaton");
		JPanel contentPane = new CellularAutomaton();
		
		_frame.add(contentPane);
		_frame.setSize(1500,1000);
		_frame.setResizable(false);
		_frame.setLocationRelativeTo(null);
		_frame.pack();
		_frame.setVisible(true);
	
	}
	
	private void setEventHandlers()
	{
		
		_newAutomaton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AutomatonMenu.startAutomatonMenu(_frame, _automaton);
				/*int[] b = {3};
				int[] s = {1,2,3,4,5};
				_automaton.newGame(250, b, s);*/
			}
		});
		
		_toggleAutomaton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_automaton.toggleTimer();
			}
		});
	}
	
}
