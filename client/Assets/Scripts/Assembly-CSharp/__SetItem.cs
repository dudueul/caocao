using System;
using System.Collections.Generic;

[Serializable]
public class __SetItem
{
	public string name;

	public int id;

	public int type;

	public int count;

	public List<int> item;

	public List<int> min;

	public List<int> max;

	public List<int> rate;

	public void CopyTo(SetItem dest)
	{
	}

	public void Reset()
	{
	}
}
