using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class WorldBossPhaseInfo : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredString m_yyyymmdd;

	private ObscuredInt m_phase;

	private ObscuredInt m_worldbossId;

	private ObscuredInt m_paymentPoint;

	private ObscuredInt m_participationCount;

	private ObscuredInt m_freeParticipatedCount;

	private ObscuredULong m_lastDamage;

	private ObscuredULong m_maxDamage;

	private ObscuredBool m_rewardReceived;

	private DateTime m_releaseDate;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public string yyyymmdd
	{
		get
		{
			return null;
		}
	}

	public int phase
	{
		get
		{
			return 0;
		}
	}

	public int worldbossId
	{
		get
		{
			return 0;
		}
	}

	public int paymentPoint
	{
		get
		{
			return 0;
		}
	}

	public int participationCount
	{
		get
		{
			return 0;
		}
	}

	public int freeParticipatedCount
	{
		get
		{
			return 0;
		}
	}

	public ulong lastDamage
	{
		get
		{
			return 0uL;
		}
	}

	public ulong maxDamage
	{
		get
		{
			return 0uL;
		}
	}

	public bool rewardReceived
	{
		get
		{
			return false;
		}
	}

	public bool isReleased
	{
		get
		{
			return false;
		}
	}

	public int remainParticipationCount
	{
		get
		{
			return 0;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
