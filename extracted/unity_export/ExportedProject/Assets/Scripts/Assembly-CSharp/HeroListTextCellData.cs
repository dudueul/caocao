using System;

public class HeroListTextCellData : IReuseCellData
{
	public int m_Index;

	public UserUnit userUnit;

	public bool isFocused;

	public EnumData.SortType[] sortTypes;

	public Action<UserUnit> OnSelectUnit;

	public int Index
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}
}
