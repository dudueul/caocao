using System.Collections.Generic;

public class JobRelations : PermanentBase
{
	public List<JobRelation> data;

	private Dictionary<int, Dictionary<int, int>> m_jobRelations;

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

	private void RefineData(_JobRelations src)
	{
	}

	public static void Load(ref JobRelations res)
	{
	}
}
