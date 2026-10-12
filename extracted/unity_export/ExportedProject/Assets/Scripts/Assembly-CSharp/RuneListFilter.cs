using System.Collections.Generic;

public class RuneListFilter
{
	public Dictionary<string, List<int>> data;

	public int maxTier;

	// C# has no syntax for parameterized property 'Item'.
	public List<int> get_Item(string key)
	{
		return null;
	}

	public void InitData(EnumData.RuneFilterMode mode)
	{
	}

	public void AddData(EnumData.RuneFilterType type, EnumData.RuneFilterMode mode)
	{
	}

	private bool IsFilterdCondition(EnumData.RuneFilterType filterType, int checkId)
	{
		return false;
	}

	private List<int> GetList(EnumData.RuneFilterType filterType)
	{
		return null;
	}

	private List<int> GetRuneSetList(EnumData.RuneFilterType filterType)
	{
		return null;
	}

	protected void _InitData(EnumData.RuneFilterType filterType, EnumData.RuneFilterMode mode)
	{
	}

	public void InitData(EnumData.RuneFilterType filterType, ref List<int> list)
	{
	}

	public static string GetKey(EnumData.RuneFilterType type)
	{
		return null;
	}

	public bool IsCheckedFilter(UserRuneItem userRune)
	{
		return false;
	}
}
