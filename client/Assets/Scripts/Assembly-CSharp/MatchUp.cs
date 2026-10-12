using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class MatchUp : ITransmittable
{
	private ObscuredInt m_townId;

	private ObscuredULong m_opponentId;

	private EnumData.townStatusType m_townStatus;

	private ObscuredBool m_VirtualUser;

	private ObscuredInt m_plunderFood;

	private ObscuredInt m_plunderSilver;

	private DateTime m_limitTime;

	public ObscuredInt townId
	{
		get
		{
			return default(ObscuredInt);
		}
	}

	public ObscuredULong opponentId
	{
		get
		{
			return default(ObscuredULong);
		}
	}

	public EnumData.townStatusType townStatus
	{
		get
		{
			return EnumData.townStatusType.none;
		}
	}

	public ObscuredBool virtualUser
	{
		get
		{
			return default(ObscuredBool);
		}
	}

	public ObscuredInt plunderFood
	{
		get
		{
			return default(ObscuredInt);
		}
	}

	public ObscuredInt plunderSilver
	{
		get
		{
			return default(ObscuredInt);
		}
	}

	public int remainSec
	{
		get
		{
			return 0;
		}
	}

	public DateTime limitTime
	{
		get
		{
			return default(DateTime);
		}
	}

	public bool isExpired
	{
		get
		{
			return false;
		}
	}

	public Action deserializeCallback { get; set; }

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
