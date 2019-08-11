package form;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

import automaton.Automaton;

public class OptionsMenu extends Form
{
	
	private JComboBox _deadCellColor;
	private JComboBox _liveCellColor;
	
	

	public OptionsMenu(JFrame frame, Automaton automaton)
	{
		super(frame, automaton);
		
		// TODO Implement components and layout
	}
	
	public static void startOptionsMenu(JFrame frame, Automaton automaton)
	{
		OptionsMenu menu = new OptionsMenu(frame, automaton);
		menu.setTitle("Options");
	}

}
