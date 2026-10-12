using System;
using System.Collections.Generic;

[Serializable]
public class __DailyGift
{
	public string name;

	public int id;

	public int giftType;

	public int day;

	public int eventdailygift;

	public int eventgiftgrade;

	public List<int> type;

	public List<int> reward;

	public List<int> mail;

	public void CopyTo(DailyGift dest)
	{
	}

	public void Reset()
	{
	}
}
