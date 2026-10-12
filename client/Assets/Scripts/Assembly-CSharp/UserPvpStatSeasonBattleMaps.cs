using System.Collections.Generic;
using LitJson;

public class UserPvpStatSeasonBattleMaps : ITransmittable, iTransmitContainer
{
	private Dictionary<int, UserPvpStatSeasonBattleMap> m_PvpStatSeasonBattleMaps;

	public Dictionary<int, UserPvpStatSeasonBattleMap> pvpStatSeasonBattleMaps
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserPvpStatSeasonBattleMap get_Item(int battleMapId)
	{
		return null;
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
