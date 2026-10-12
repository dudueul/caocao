using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using CustomEvent;
using LitJson;

public class CustomEventIngame : ITransmittable, IEventSchedule
{
	private ObscuredULong m_id;

	private ObscuredInt m_orderId;

	private ObscuredInt m_type;

	private ObscuredString m_title;

	private ObscuredString m_description;

	private ObscuredString m_imagePath;

	private DateTime m_startDate;

	private DateTime m_endDate;

	private DateTime m_updDate;

	private DateTime m_regDate;

	private ObscuredInt m_countryCode;

	public ulong id
	{
		get
		{
			return 0uL;
		}
	}

	public int orderId
	{
		get
		{
			return 0;
		}
	}

	public int type
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

	public string imagePath
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

	public List<IEventFilter> filters { get; private set; }

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

	public bool checkFilter()
	{
		return false;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
