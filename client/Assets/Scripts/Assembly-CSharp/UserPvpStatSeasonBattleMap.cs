using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserPvpStatSeasonBattleMap : UserPvpStatBattleMap
{
	private ObscuredString m_limitDay;

	public string limitDay
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
