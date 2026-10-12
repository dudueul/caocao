using System;
using System.Collections.Generic;

[Serializable]
public class __Storybook
{
	public string name;

	public int id;

	public string image;

	public int preStorybook;

	public int classicModeFlag;

	public int goodsType;

	public int price;

	public int gold;

	public int language;

	public int country;

	public int classicCountry;

	public int rollBack;

	public int serialization;

	public string dayofWeek;

	public List<int> needUnit;

	public List<int> whitelist;

	public void CopyTo(Storybook dest)
	{
	}

	public void Reset()
	{
	}
}
