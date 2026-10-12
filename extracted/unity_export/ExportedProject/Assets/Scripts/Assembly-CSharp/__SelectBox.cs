using System;
using System.Collections.Generic;

[Serializable]
public class __SelectBox
{
	public string name;

	public int id;

	public int country;

	public int boxType;

	public int boxOption;

	public int bonusBoxType;

	public List<int> refreshcost;

	public string boxEffect;

	public string title;

	public string desc;

	public void CopyTo(SelectBox dest)
	{
	}

	public void Reset()
	{
	}
}
