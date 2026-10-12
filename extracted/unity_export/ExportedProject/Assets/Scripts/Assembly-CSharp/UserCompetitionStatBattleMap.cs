using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserCompetitionStatBattleMap : UserCompetitionStat
{
	private ObscuredInt m_battleMapId;

	public int battleMapId
	{
		get
		{
			return 0;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
