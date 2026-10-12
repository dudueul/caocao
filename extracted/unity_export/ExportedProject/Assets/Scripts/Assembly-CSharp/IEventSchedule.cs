using System;
using LitJson;

public interface IEventSchedule
{
	ulong id { get; }

	string title { get; }

	DateTime startDate { get; }

	DateTime endDate { get; }

	TimeSpan RemainTime { get; }

	bool IsActivated { get; }

	void Deserialize(JsonData jsonData);
}
