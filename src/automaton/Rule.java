package automaton;

public class Rule
{

	private int[] _birth;
	private int[] _survival;
	
	public Rule(int[] B, int[] S)
	{
		_birth = B;
		_survival = S;
	}
	
	public int[] getB()
	{
		return _birth;
	}
	
	public int[] getS()
	{
		return _survival;
	}
	
	@Override
	public String toString()
	{
		String rulestring = "B";
		for (int num : _birth)
		{
			rulestring += num;
		}
		rulestring += "/S";
		for (int num : _survival)
		{
			rulestring += num;
		}
		return rulestring;
	}
}
