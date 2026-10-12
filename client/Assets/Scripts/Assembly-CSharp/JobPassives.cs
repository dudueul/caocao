using System.Collections.Generic;

public class JobPassives : PermanentBase
{
	public List<JobPassive> data;

	private Dictionary<int, Dictionary<int, int>> m_jobPassives;

	public Dictionary<int, Dictionary<int, int>> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Dictionary<int, int> get_Item(int left)
	{
		return null;
	}

	// C# has no syntax for parameterized property 'Item'.
	public int get_Item(int left, int right)
	{
		return 0;
	}

	private void RefineData(_JobPassives src)
	{
	}

	public static void Load(ref JobPassives res)
	{
	}
}
