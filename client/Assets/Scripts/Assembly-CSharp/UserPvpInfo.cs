using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserPvpInfo : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredInt m_leagueId;

	private ObscuredString m_limitDay;

	private ObscuredInt m_point;

	private ObscuredInt m_maxPoint;

	private ObscuredInt m_streakWinCnt;

	private ObscuredInt m_rewardRecieved;

	private DateTime m_regDate;

	private DateTime m_updDate;

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

	public int leagueId
	{
		get
		{
			return 0;
		}
	}

	public string limitDay
	{
		get
		{
			return null;
		}
	}

	public int point
	{
		get
		{
			return 0;
		}
	}

	public int maxPoint
	{
		get
		{
			return 0;
		}
	}

	public int streakWinCnt
	{
		get
		{
			return 0;
		}
	}

	public int rewardRecieved
	{
		get
		{
			return 0;
		}
	}

	public Action deserializeCallback { get; set; }

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
