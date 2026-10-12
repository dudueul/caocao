using System.Collections.Generic;

public class HeroListFilter
{
	public Dictionary<string, List<int>> data;

	// C# has no syntax for parameterized property 'Item'.
	public List<int> get_Item(string key)
	{
		return null;
	}

	public void InitData(EnumData.FilterMode mode = EnumData.FilterMode.BattleDeck)
	{
	}

	public void AddData(EnumData.DeckFilterType type)
	{
	}

	private bool IsFilterdCondition(EnumData.DeckFilterType filterType, int checkId)
	{
		return false;
	}

	private List<int> GetList(EnumData.DeckFilterType filterType)
	{
		return null;
	}

	protected void _InitData(EnumData.DeckFilterType filterType)
	{
	}

	public static void InitData(EnumData.DeckFilterType filterType, ref List<int> list)
	{
	}

	public static string GetKey(EnumData.DeckFilterType type)
	{
		return null;
	}

	public bool IsCheckedFilter(UserUnit userUnit, bool isBattleDeckSet)
	{
		return false;
	}

	private int GetUnitGradeValue(int unitId)
	{
		return 0;
	}
}
