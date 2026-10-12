using System.Collections.Generic;

public class EventListFilter
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

	public void AddData(EnumData.EventFilterType type)
	{
	}

	public bool IsFilterdCondition(EnumData.EventFilterType filterType, int checkId)
	{
		return false;
	}

	private bool GetConditionByEnemyLevel(int checkId)
	{
		return false;
	}

	private bool GetConditionByReward(int checkId)
	{
		return false;
	}

	private List<int> GetList(EnumData.EventFilterType filterType)
	{
		return null;
	}

	protected void _InitData(EnumData.EventFilterType filterType)
	{
	}

	public static void InitData(EnumData.EventFilterType filterType, ref List<int> list)
	{
	}

	public static string GetKey(EnumData.EventFilterType type)
	{
		return null;
	}
}
