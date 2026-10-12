using System;
using System.Collections.Generic;

[Serializable]
public class __CharType
{
	public string name;

	public int id;

	public List<string> criticalMsg;

	public int criticalRate;

	public int autoRebirth;

	public int deathcounter;

	public void CopyTo(CharType dest)
	{
	}

	public void Reset()
	{
	}
}
