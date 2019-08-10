package form;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import automaton.Automaton;

public class AutomatonMenu extends JDialog
{
	
	private static final long serialVersionUID = 1L;

	private Automaton _automaton;
	
	private JLabel _textForRuleB;
	private JLabel _textForRuleS;
	private JLabel _textForGridSize;
	private JLabel _textForLiveCells;
	
	private JTextField _ruleInputB;
	private JTextField _ruleInputS;
	private JComboBox<Integer> _gridSizes;
	private JComboBox<Integer> _liveCellPcts;
	
	private JLabel _errorMsg;
	private JButton _submit;

	public AutomatonMenu(JFrame frame, Automaton automaton)
	{
		super(frame, "Create a new automaton");
		setResizable(false);
		
		_automaton = automaton;
		
		//Using GridBagLayout to do the layout of components in the content pane
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 1;
		
		//Elements on left side (labels describing input options)
		
		c.gridx = 0;
		
		_textForRuleB = new JLabel("RuleString B: ", SwingConstants.RIGHT);
		_textForRuleB.setFont(new Font("Helvetica", Font.BOLD, 15));
		c.gridy = 0;
		add(_textForRuleB, c);
		
		_textForRuleS = new JLabel("RuleString S: ", SwingConstants.RIGHT);
		_textForRuleS.setFont(new Font("Helvetica", Font.BOLD, 15));
		c.gridy = 1;
		add(_textForRuleS, c);
		
		_textForGridSize = new JLabel("Size of Grid Square: ", SwingConstants.RIGHT);
		_textForGridSize.setFont(new Font("Helvetica", Font.BOLD, 15));
		c.gridy = 2;
		add(_textForGridSize, c);
		
		_textForLiveCells = new JLabel("Live Cell Percentage: ", SwingConstants.RIGHT);
		_textForLiveCells.setFont(new Font("Helventica", Font.BOLD, 15));
		c.gridy = 3;
		add(_textForLiveCells, c);
		
		
		//Elements on right side (text fields, drop down menus to get user input)
		
		c.gridx = 1;
		c.insets = new Insets(0,0,0,85);
		
		_ruleInputB = new JTextField();
		c.gridy = 0;
		add(_ruleInputB, c);
		
		_ruleInputS = new JTextField();
		c.gridy = 1;
		add(_ruleInputS, c);
		
		Integer[] gridSizeOptions = { 1000, 500, 250, 200, 125, 100, 50, 40, 25 };
		_gridSizes = new JComboBox<Integer>(gridSizeOptions);
		_gridSizes.setBackground(Color.WHITE);
		c.gridy = 2;
		add(_gridSizes, c);
		
		Integer[] liveCellPercentages = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		_liveCellPcts = new JComboBox<Integer>(liveCellPercentages);
		_liveCellPcts.setBackground(Color.WHITE);
		c.gridy = 3;
		add(_liveCellPcts, c);
		
		//Error message label
		
		c.insets = new Insets(0,0,0,0);
		c.gridx = 0;
		c.gridwidth = 2;
		
		_errorMsg = new JLabel();
		_errorMsg.setFont(new Font("Helvetica", Font.ITALIC, 15));
		_errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		_errorMsg.setForeground(this.getBackground());
		c.gridy = 4;
		add(_errorMsg, c);
		_errorMsg.setText("...nothing to report");
		
		//"Submit" button at the bottom of the form
		c.insets = new Insets(0,120,0,120);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		
		_submit = new JButton("CREATE AUTOMATON");
		_submit.setFont(new Font("Helvetica", Font.BOLD, 15));
		addButtonEventHandler();
		c.gridy = 5;
		add(_submit, c);
		
		this.setSize(500,600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
	
	public static void startAutomatonMenu(JFrame frame, Automaton automaton)
	{
		AutomatonMenu menu = new AutomatonMenu(frame, automaton);
	}
	
	
	
	private void addButtonEventHandler()
	{
		_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					char[] bCharArray = _ruleInputB.getText().toCharArray();
					char[] sCharArray = _ruleInputS.getText().toCharArray();
					
					if (bCharArray.length<1 || sCharArray.length<1)
					{
						throw new FormException("One or both of the RuleString inputs is empty.");
					}
					
					int[] ruleB = new int[bCharArray.length];
					int[] ruleS = new int[sCharArray.length];
					
					
					for (int i = 0; i < bCharArray.length; i++)
					{
						if (!Character.isDigit(bCharArray[i]))
						{
							throw new FormException("RuleString B contains a non-numeric character.");
						}
						ruleB[i] = Character.getNumericValue(bCharArray[i]);
					}
					
					for (int i = 0; i < sCharArray.length; i++)
					{
						if (!Character.isDigit(sCharArray[i]))
						{
							throw new FormException("RuleString S contains a non-numeric character.");
						}
						ruleS[i] = Character.getNumericValue(sCharArray[i]);
					}
					
					int size = (Integer) _gridSizes.getSelectedItem();
					int liveCellPct = (Integer) _liveCellPcts.getSelectedItem();
					
					_automaton.newAutomaton(size, liveCellPct, ruleB, ruleS);
					dispose();
				}
				catch (FormException formEx)
				{
					_errorMsg.setForeground(Color.RED);
					_errorMsg.setText(formEx.getMessage());
				}
				
			}
		});
	}
}
