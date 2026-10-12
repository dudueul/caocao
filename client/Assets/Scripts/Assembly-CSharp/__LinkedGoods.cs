using System;
using System.Collections.Generic;

[Serializable]
public class __LinkedGoods
{
	public int id;

	public string title;

	public string desc;

	public string startDate;

	public string endDate;

	public int country;

	public int cycleCount;

	public List<int> shopType;

	public List<int> productID;

	public List<int> saleCount;

	public void CopyTo(LinkedGoods dest)
	{
	}

	public void Reset()
	{
	}
}
