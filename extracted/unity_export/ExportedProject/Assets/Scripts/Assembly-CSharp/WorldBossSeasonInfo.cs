using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class WorldBossSeasonInfo : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredString m_yyyymmdd;

	private ObscuredULong m_totalToken;

	private ObscuredULong m_token;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public string yyyymmdd
	{
		get
		{
			return null;
		}
	}

	public ulong totalToken
	{
		get
		{
			return 0uL;
		}
	}

	public ulong token
	{
		get
		{
			return 0uL;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
