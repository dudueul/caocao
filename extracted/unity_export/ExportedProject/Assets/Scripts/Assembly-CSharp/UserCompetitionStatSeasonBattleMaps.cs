using System.Collections.Generic;
using LitJson;

public class UserCompetitionStatSeasonBattleMaps : ITransmittable, iTransmitContainer
{
	private Dictionary<int, UserCompetitionStatSeasonBattleMap> m_competitionStatSeasonBattleMaps;

	public Dictionary<int, UserCompetitionStatSeasonBattleMap> competitionStatSeasonBattleMaps
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserCompetitionStatSeasonBattleMap get_Item(int battleMapId)
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
