using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserCompetitionStatSeason : UserCompetitionStat
{
	private ObscuredString m_limitDay;

	public string limitDay
	{
		get
		{
			return null;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
