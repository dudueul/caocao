using System;

[Serializable]
public class __TradeShop
{
	public int id;

	public int item;

	public int lordLv;

	public int goodType;

	public int cost;

	public string weekDay;

	public int minNum;

	public int maxNum;

	public int ratio;

	public string slot;

	public int country;

	public void CopyTo(TradeShop dest)
	{
	}

	public void Reset()
	{
	}
}
