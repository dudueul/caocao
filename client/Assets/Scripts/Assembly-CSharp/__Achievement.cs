using System;
using System.Collections.Generic;

[Serializable]
public class __Achievement
{
	public string name;

	public int id;

	public int group;

	public string icon;

	public int isStack;

	public int isHidden;

	public int category;

	public int checkId;

	public int checkType;

	public int checkVal;

	public int countVal;

	public int type0;

	public int reward0;

	public string desc;

	public List<string> gpgsId;

	public int enableClassic;

	public int country;

	public int showToast;

	public void CopyTo(Achievement dest)
	{
	}

	public void Reset()
	{
	}
}
