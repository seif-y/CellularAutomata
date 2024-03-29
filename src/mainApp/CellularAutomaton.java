package mainApp;

//import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import automaton.Automaton;
import form.AutomatonMenu;
import form.OptionsMenu;

public class CellularAutomaton extends JPanel
{

	private static final long serialVersionUID = 1L;
	
	private static JFrame _frame;
	
	private Automaton _automaton;
	
	private JButton _btnNew;
	private JButton _btnToggle;
	private JButton _btnOptions;
	private JSlider _speedControl;
	
	private CellularAutomaton()
	{
		setLayout(new BorderLayout());
		
		// SETTING UP BUTTONS
		
		_btnNew = new JButton("NEW");
		_btnToggle = new JButton("START");
		_btnOptions = new JButton("OPTIONS");
		_speedControl = new JSlider(1,100);
		setEventHandlers();
		
		// SETTING UP INFO PANEL
		
		JPanel infoPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		infoPanel.add(_btnNew, c);
		c.gridy = 1;
		infoPanel.add(_btnToggle, c);
		c.gridy = 2;
		infoPanel.add(_btnOptions, c);
		c.gridy = 3;
		infoPanel.add(_speedControl, c);
		infoPanel.setPreferredSize(new Dimension(500,1000));
		add(infoPanel, BorderLayout.WEST);
		
		//SETTING UP AUTOMATON VIEW
		
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
		
		_btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				AutomatonMenu.startAutomatonMenu(_frame, _automaton);
				/*int[] b = {3};
				int[] s = {1,2,3,4,5};
				_automaton.newGame(250, b, s);*/
			}
		});
		
		_btnToggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				_automaton.toggleTimer();
				
				String text = _btnToggle.getText();
				if (text.equals("START")) { _btnToggle.setText("STOP"); }
				else { _btnToggle.setText("START"); }
			}
		});
		
		_btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				OptionsMenu.startOptionsMenu(_frame, _automaton);
			}
		});
		
		_speedControl.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e)
			{
				int speed = _speedControl.getValue();
				_automaton.setTimerDelay(1000/speed);
			}
		});
	}
	
}
