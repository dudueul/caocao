using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserCompetitionInfo : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredString m_limitDay;

	private ObscuredInt m_battleMapId;

	private ObscuredInt m_mmr;

	private ObscuredInt m_point;

	private ObscuredInt m_maxPoint;

	private ObscuredInt m_streakWinCnt;

	private ObscuredInt m_rewardRecieved;

	private ObscuredInt m_tierGrade;

	private ObscuredInt m_tierWarning;

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

	public string limitDay
	{
		get
		{
			return null;
		}
	}

	public int battleMapId
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

	public int tierGrade
	{
		get
		{
			return 0;
		}
	}

	public int tierWarning
	{
		get
		{
			return 0;
		}
	}

	public Action deserializeCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
