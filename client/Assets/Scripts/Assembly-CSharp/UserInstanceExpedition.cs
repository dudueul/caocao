using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserInstanceExpedition : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	public List<int> Instances;

	public Action updateCallback;

	public DateTime m_startDate { get; private set; }

	public DateTime m_endDate { get; private set; }

	public ulong Srl
	{
		get
		{
			return 0uL;
		}
	}

	public DateTime StartDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime EndDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public int Unit1 { get; private set; }

	public int Unit2 { get; private set; }

	public int Unit3 { get; private set; }

	public int Unit4 { get; private set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public TimeSpan GetExpeditionRemainTime()
	{
		return default(TimeSpan);
	}

	public EnumData.ExpeditionStateType GetExpeditionStateType()
	{
		return EnumData.ExpeditionStateType.None;
	}

	public int CalculateExpeditionTime(int totalCombatPower)
	{
		return 0;
	}
}
