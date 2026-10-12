using System;
using System.Collections.Generic;

[Serializable]
public class __SetTimeBox
{
	public string name;

	public int id;

	public int type;

	public int count;

	public List<int> timeBox;

	public List<int> min;

	public List<int> max;

	public List<int> rate;

	public void CopyTo(SetTimeBox dest)
	{
	}

	public void Reset()
	{
	}
}
