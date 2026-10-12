using System.Collections.Generic;
using LitJson;

public class UserPvpStatSeasons : ITransmittable, iTransmitContainer
{
	private Dictionary<string, UserPvpStatSeason> m_PvpStatSeasons;

	public Dictionary<string, UserPvpStatSeason> pvpStatSeasons
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserPvpStatSeason get_Item(string limitDay)
	{
		return null;
	}

	public UserPvpStatSeason getLatest
	{
		get
		{
			return null;
		}
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
