using System;
using System.Collections.Generic;

[Serializable]
public class __TierUp
{
	public string name;

	public int id;

	public List<int> tier;

	public List<int> atkUp;

	public List<int> defUp;

	public List<int> intUp;

	public List<int> agiUp;

	public List<int> morUp;

	public void CopyTo(TierUp dest)
	{
	}

	public void Reset()
	{
	}
}
