using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class CustomEventHotTime : ITransmittable, IEventSchedule
{
	private ObscuredULong m_id;

	private ObscuredInt m_contentType;

	private ObscuredInt m_eventType;

	private ObscuredString m_title;

	private ObscuredString m_description;

	private JsonData m_rewardIds;

	private DateTime m_startDate;

	private DateTime m_endDate;

	private ObscuredInt m_startHour;

	private ObscuredInt m_endHour;

	private ObscuredInt m_weekDay;

	private DateTime m_updDate;

	private DateTime m_regDate;

	private ObscuredInt m_countryCode;

	public DateTime m_endTime;

	public List<HotTimeReward> rewardDatas;

	public string rewardDescs;

	public ulong id
	{
		get
		{
			return 0uL;
		}
	}

	public int contentType
	{
		get
		{
			return 0;
		}
	}

	public int eventType
	{
		get
		{
			return 0;
		}
	}

	public string title
	{
		get
		{
			return null;
		}
	}

	public string description
	{
		get
		{
			return null;
		}
	}

	public JsonData rewardIds
	{
		get
		{
			return null;
		}
	}

	public DateTime startDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime endDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public int startHour
	{
		get
		{
			return 0;
		}
	}

	public int endHour
	{
		get
		{
			return 0;
		}
	}

	public int weekDay
	{
		get
		{
			return 0;
		}
	}

	public DateTime updDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime regDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public TimeSpan RemainTime
	{
		get
		{
			return default(TimeSpan);
		}
	}

	public bool IsActivated
	{
		get
		{
			return false;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
