package form;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;

import automaton.Automaton;

public abstract class Form extends JDialog
{
	
	protected Automaton _automaton;
	protected GridBagConstraints _GBC;
	
	public Form(JFrame frame, Automaton automaton)
	{
		super(frame);
		setResizable(false);
		setSize(500,600);
		setLocationRelativeTo(null);
		setVisible(true);
		
		setLayout(new GridBagLayout());
		_GBC = new GridBagConstraints();
		
		_automaton = automaton;
	}

}
