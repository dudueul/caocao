using System.Collections.Generic;

public class GuildWarTileCorpsCosts : PermanentBase
{
	public List<GuildWarTileCorpsCost> data;

	private Dictionary<int, Dictionary<int, int>> m_guildWarTileCorpsCosts;

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

	private void RefineData(_GuildWarTileCorpsCosts src)
	{
	}

	public static void Load(ref GuildWarTileCorpsCosts res)
	{
	}
}
