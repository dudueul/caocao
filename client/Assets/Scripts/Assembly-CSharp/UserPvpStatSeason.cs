using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserPvpStatSeason : UserPvpStat
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
