using System;
using System.Collections.Generic;

[Serializable]
public class __TimeBoxRatio
{
	public int id;

	public int boxId;

	public int itemId;

	public int goodsType;

	public int treasureType;

	public int slot;

	public int itemtier;

	public List<int> min;

	public List<int> max;

	public List<int> itemRatio;

	public int cardTier;

	public int multiple;

	public void CopyTo(TimeBoxRatio dest)
	{
	}

	public void Reset()
	{
	}
}
