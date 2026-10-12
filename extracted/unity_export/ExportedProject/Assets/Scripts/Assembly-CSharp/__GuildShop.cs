using System;
using System.Collections.Generic;

[Serializable]
public class __GuildShop
{
	public int id;

	public int item;

	public int openGrade;

	public int goodType;

	public int cost;

	public List<int> num;

	public int order;

	public string startDate;

	public string endDate;

	public void CopyTo(GuildShop dest)
	{
	}

	public void Reset()
	{
	}
}
