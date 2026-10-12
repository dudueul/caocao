using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class GuildSiegeWarBattleDef : ITransmittable
{
	private ObscuredULong m_battleId;

	private ObscuredULong m_leagueId;

	private ObscuredULong m_warId;

	private ObscuredInt m_facilityId;

	private ObscuredULong m_userId;

	private ObscuredULong m_atkUserId;

	private ObscuredBool m_atkWin;

	private ObscuredInt m_atkPoint;

	private ObscuredInt m_defPoint;

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

	public ulong atkUserId
	{
		get
		{
			return 0uL;
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

	public int defPoint
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
