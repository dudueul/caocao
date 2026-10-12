using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class GuildSiegeWarFacility : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_guildId;

	private ObscuredInt m_facilityId;

	private ObscuredInt m_exp;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong guildId
	{
		get
		{
			return 0uL;
		}
	}

	public int facilityId
	{
		get
		{
			return 0;
		}
	}

	public int exp
	{
		get
		{
			return 0;
		}
	}

	public int level
	{
		get
		{
			return 0;
		}
	}

	public GuildSiegeWarFacility()
	{
	}

	public GuildSiegeWarFacility(ulong guildId, GuildSiegeTownFacility facility)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void Clear()
	{
	}
}
