using System;
using System.Collections.Generic;

[Serializable]
public class __MarketShop
{
	public string name;

	public int id;

	public int lv;

	public string weekDay;

	public List<int> itemId;

	public List<int> goodType;

	public List<int> cost;

	public void CopyTo(MarketShop dest)
	{
	}

	public void Reset()
	{
	}
}
