using System;
using LitJson;

public class EventShopInfo : IEventSchedule
{
	public ulong id { get; private set; }

	public int scope { get; private set; }

	public string title { get; private set; }

	public DateTime startDate { get; private set; }

	public DateTime endDate { get; private set; }

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

	public EventShopInfo()
	{
	}

	public EventShopInfo(JsonData jsonData)
	{
	}

	public void Deserialize(JsonData jsonData)
	{
	}
}
