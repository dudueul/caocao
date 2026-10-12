using System.Collections.Generic;

public class TileJobCosts : PermanentBase
{
	public List<TileJobCost> data;

	private Dictionary<int, Dictionary<int, int>> m_tileJobCosts;

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

	private void RefineData(_TileJobCosts src)
	{
	}

	public static void Load(ref TileJobCosts res)
	{
	}
}
