using System.Collections.Generic;
using LitJson;

public class EventScheduleData<EventSchedule> where EventSchedule : IEventSchedule
{
	private class ScheduleComparer : IEqualityComparer<EventSchedule>
	{
		public bool Equals(EventSchedule x, EventSchedule y)
		{
			return false;
		}

		public int GetHashCode(EventSchedule obj)
		{
			return 0;
		}
	}

	private List<EventSchedule> _oldData;

	private static ScheduleComparer scheduleComparer;

	public IEnumerable<EventSchedule> added { get; private set; }

	public IEnumerable<EventSchedule> deleted { get; private set; }

	public bool IsActivate
	{
		get
		{
			return false;
		}
	}

	public bool HasNew
	{
		get
		{
			return false;
		}
	}

	public void Deserialize(JsonData jsonData)
	{
	}

	public void Upsert(List<EventSchedule> scheduleList)
	{
	}
}
