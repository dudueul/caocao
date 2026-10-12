using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserPvpStatBattleMap : UserPvpStat
{
	private ObscuredInt m_battleMapId;

	public int battleMapId
	{
		get
		{
			return 0;
		}
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
