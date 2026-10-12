using System.Collections.Generic;
using LitJson;

public class UserCompetitionStatBattleMaps : ITransmittable, iTransmitContainer
{
	private Dictionary<int, UserCompetitionStatBattleMap> m_competitionStatBattleMaps;

	public Dictionary<int, UserCompetitionStatBattleMap> competitionStatBattleMaps
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserCompetitionStatBattleMap get_Item(int battleMapId)
	{
		return null;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
