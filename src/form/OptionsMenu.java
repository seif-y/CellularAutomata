package form;

import java.awt.Color;
import java.awt.GridBagConstraints;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import automaton.Automaton;

public class OptionsMenu extends Form
{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel _textForColor;
	private JLabel _deadCells;
	private JLabel _liveCells;
	
	private JComboBox<String> _deadCellColor;
	private JComboBox<String> _liveCellColor;
	
	

	public OptionsMenu(JFrame frame, Automaton automaton)
	{
		super(frame, automaton);
		
		_GBC.fill = GridBagConstraints.HORIZONTAL;
		_GBC.weightx = 1;
		_GBC.weighty = 1;
		
		_textForColor = new JLabel("Change colours: ");
		_textForColor.setHorizontalAlignment(SwingConstants.RIGHT);
		_textForColor.setBackground(Color.BLUE);
		_GBC.gridx = 0;
		_GBC.gridy = 0;
		_GBC.gridheight = 2;
		add(_textForColor, _GBC);
		
		_deadCells = new JLabel("Dead cells");
		_GBC.gridx = 1;
		_GBC.gridheight = 1;
		add(_deadCells, _GBC);
		
		_liveCells = new JLabel("Live cells");
		_GBC.gridx = 2;
		add(_liveCells, _GBC);
		
		String[] a7a = {"1", "2", "3" };
		
		_deadCellColor = new JComboBox<String>(a7a);
		_GBC.gridy = 1;
		_GBC.gridx = 1;
		add(_deadCellColor, _GBC);
		
		_liveCellColor = new JComboBox<String>(a7a);
		_GBC.gridx = 2;
		add(_liveCellColor, _GBC);
	}
	
	public static void startOptionsMenu(JFrame frame, Automaton automaton)
	{
		OptionsMenu menu = new OptionsMenu(frame, automaton);
		menu.setTitle("Options");
	}

}
