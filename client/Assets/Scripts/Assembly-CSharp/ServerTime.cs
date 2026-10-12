using System;
using LitJson;

public class ServerTime
{
	private static DateTime ms_serverLocalTime;

	private static float ms_assignedTime;

	private static DateTime ms_serverTime;

	public static DateTime serverTime
	{
		set
		{
		}
	}

	public static DateTime Now
	{
		get
		{
			return default(DateTime);
		}
	}

	public static TimeSpan timeDiff
	{
		get
		{
			return default(TimeSpan);
		}
	}

	public static Action deserializeCallback { get; set; }

	public static double GetNowUnixTime
	{
		get
		{
			return 0.0;
		}
	}

	public static void Deserialize(JsonData jsonData)
	{
	}

	public static TimeSpan GetRemainedTimeSpanTo(DayOfWeek dayOfWeek, int hour)
	{
		return default(TimeSpan);
	}

	public static TimeSpan GetRemainedTimeSpanTo(int addDay, int hour)
	{
		return default(TimeSpan);
	}

	public static DateTime GetDateTime(ulong milliseconds)
	{
		return default(DateTime);
	}
}
