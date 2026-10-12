using System;
using System.Collections.Generic;

public class RankTitleMenu : IRankMenuItem
{
	private List<RankSubMenu> m_subMenuList;

	public int DefaultRankId
	{
		get
		{
			return 0;
		}
	}

	public EnumData.rankGroup rankGroup { get; private set; }

	public EnumData.rankType rankType
	{
		get
		{
			return EnumData.rankType.Indicator;
		}
	}

	public string rankName { get; private set; }

	public List<RankSubMenu> subMenuList
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public RankSubMenu get_Item(int index)
	{
		return null;
	}

	public RankTitleMenu(EnumData.rankGroup rankGroup)
	{
	}

	public void AddSubMenu(RankSubMenu subMenu)
	{
	}

	public void OnClick()
	{
	}

	public void SetOnClickHandler(Action<int> onClick)
	{
	}
}
