using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserCompetitionStat : ITransmittable, ICompetitionStat
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredInt m_win;

	private ObscuredInt m_lose;

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

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
