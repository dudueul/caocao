using System;
using System.Collections.Generic;

[Serializable]
public class __TimeBox
{
	public string name;

	public int id;

	public int country;

	public int infoType;

	public int isVisible;

	public int order;

	public string perDesc;

	public List<string> desc;

	public List<int> fixedRewardType;

	public List<int> fixedRewardId;

	public int openTime;

	public string icon;

	public string boxEffect;

	public int alram;

	public int drop;

	public int limit;

	public int limitCount;

	public void CopyTo(TimeBox dest)
	{
	}

	public void Reset()
	{
	}
}
