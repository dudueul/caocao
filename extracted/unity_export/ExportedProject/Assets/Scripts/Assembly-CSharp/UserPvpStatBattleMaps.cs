using System.Collections.Generic;
using LitJson;

public class UserPvpStatBattleMaps : ITransmittable, iTransmitContainer
{
	private Dictionary<int, UserPvpStatBattleMap> m_PvpStatBattleMaps;

	public Dictionary<int, UserPvpStatBattleMap> pvpStatBattleMaps
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserPvpStatBattleMap get_Item(int battleMapId)
	{
		return null;
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
