using LitJson;

public class GuildSiegeWarUserStat : ITransmittable
{
	private ulong m_userId;

	private ulong m_guildId;

	private int m_atkPoint;

	private int m_defPoint;

	public ulong userId
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

	public int atkPoint
	{
		get
		{
			return 0;
		}
	}

	public int defPoint
	{
		get
		{
			return 0;
		}
	}

	public int point
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
