using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserPvpStat : ITransmittable, IPvpStat
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredInt m_attackWin;

	private ObscuredInt m_attackLose;

	private ObscuredInt m_defenseWin;

	private ObscuredInt m_defenseLose;

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

	public int attackWin
	{
		get
		{
			return 0;
		}
	}

	public int attackLose
	{
		get
		{
			return 0;
		}
	}

	public int defenseWin
	{
		get
		{
			return 0;
		}
	}

	public int defenseLose
	{
		get
		{
			return 0;
		}
	}

	public DateTime regDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime updDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public int winCount
	{
		get
		{
			return 0;
		}
	}

	public int loseCount
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
