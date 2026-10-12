using System;
using System.Collections.Generic;

[Serializable]
public class __ExchangeShop
{
	public int id;

	public int slotType;

	public int country;

	public string Title;

	public int openItemID;

	public string startDate;

	public string endDate;

	public int dateHidden;

	public int alarmRibbon;

	public string icon;

	public int tradeItemID;

	public int getPlace;

	public int shortcutId1;

	public int shortcutId2;

	public string desc;

	public int mailId;

	public List<int> itemId;

	public List<int> goodsType;

	public List<int> amount;

	public List<int> cost;

	public List<int> maxNum;

	public List<int> storage;

	public void CopyTo(ExchangeShop dest)
	{
	}

	public void Reset()
	{
	}
}
