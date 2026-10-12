using LitJson;

public class GuildSiegeWarWarStat : ITransmittable
{
	private ulong m_srl;

	private ulong m_userId;

	private ulong m_leagueId;

	private ulong m_warId;

	private int m_atk_point;

	private int m_def_point;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong userId
	{
		get
		{
			return 0uL;
		}
	}

	public ulong leagueId
	{
		get
		{
			return 0uL;
		}
	}

	public ulong warId
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
