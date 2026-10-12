using System.Collections.Generic;

public class TreasureListFilter
{
	public Dictionary<string, List<int>> data;

	// C# has no syntax for parameterized property 'Item'.
	public List<int> get_Item(string key)
	{
		return null;
	}

	public void InitData()
	{
	}

	public void AddData(EnumData.TreasureFilterType type)
	{
	}

	private List<int> GetList(EnumData.TreasureFilterType filterType)
	{
		return null;
	}

	protected void _InitData(EnumData.TreasureFilterType filterType)
	{
	}

	public void InitData(EnumData.TreasureFilterType filterType, ref List<int> list)
	{
	}

	public static string GetKey(EnumData.TreasureFilterType type)
	{
		return null;
	}

	private bool IsFilterdCondition(EnumData.TreasureFilterType filterType, int checkId)
	{
		return false;
	}

	public bool IsCheckedFilter(UserItem userItem)
	{
		return false;
	}
}
