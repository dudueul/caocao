using LitJson;

public class GuildSiegeWarBattleAtk : ITransmittable
{
	private ulong m_battleId;

	private ulong m_leagueId;

	private ulong m_warId;

	private int m_facilityId;

	private ulong m_userId;

	private int m_defUserId;

	private bool m_atkWin;

	private int m_atkPoint;

	public ulong battleId
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

	public int facilityId
	{
		get
		{
			return 0;
		}
	}

	public ulong userId
	{
		get
		{
			return 0uL;
		}
	}

	public int defUserId
	{
		get
		{
			return 0;
		}
	}

	public bool atkWin
	{
		get
		{
			return false;
		}
	}

	public int atkPoint
	{
		get
		{
			return 0;
		}
	}

	protected override void DeserializeImpl(JsonData data)
	{
	}
}
