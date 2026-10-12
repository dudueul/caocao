using LitJson;

public class GuildSiegeWarStat : GuildDependentDataBase
{
	private ulong m_guildId;

	private int m_tier;

	private int m_mmr;

	private int m_rank;

	private int m_win;

	private int m_lose;

	public ulong guildId
	{
		get
		{
			return 0uL;
		}
	}

	public int tier
	{
		get
		{
			return 0;
		}
	}

	public int mmr
	{
		get
		{
			return 0;
		}
	}

	public int rank
	{
		get
		{
			return 0;
		}
	}

	public int win
	{
		get
		{
			return 0;
		}
	}

	public int lose
	{
		get
		{
			return 0;
		}
	}

	public GuildSiegeWarStat(GuildData guildData)
		: base(null)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
