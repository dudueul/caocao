using System;
using System.Collections.Generic;

[Serializable]
public class __Season
{
	public string name;

	public int id;

	public string round;

	public string seasonTexture;

	public int isRegular;

	public List<int> map;

	public List<int> userSpell;

	public List<int> fair;

	public int cycleTerm;

	public string startDate;

	public string endDate;

	public string rotationStartDate;

	public int country;

	public List<int> itemId;

	public void CopyTo(Season dest)
	{
	}

	public void Reset()
	{
	}
}
