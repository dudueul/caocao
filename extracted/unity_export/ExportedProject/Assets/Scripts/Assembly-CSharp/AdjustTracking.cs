using System.Collections.Generic;

public static class AdjustTracking
{
	public enum EventType
	{
		APP_OPEN = 0,
		DOWNLOAD_COMPLETE = 1,
		LOGIN = 2,
		NATION_REGISTRATION = 3,
		TUTORIAL_START = 4,
		FIRST_BATTLE_COMPLETE = 5,
		APPOINTMENT = 6,
		TUTORIAL_COMPLETE = 7,
		REAL_COMBAT_CLOSE = 8,
		PURCHASE = 9,
		YANZHOU_UNIFICATION = 10,
		PORT_LINZI_BATTLE_START = 11,
		DOWNLOAD_START = 12,
		FIRST_PURCHASE = 13,
		MAX = 14
	}

	private static Dictionary<EventType, string[]> dicToken;

	public static void Event(EventType type)
	{
	}

	public static void Event(string androidToken)
	{
	}

	public static void SetRevenue(double amount, string currency, string transactionId)
	{
	}

	public static void EventLocalization(string androidToken)
	{
	}

	private static string GetFindToken(Dictionary<EventType, string[]> dicToken, string korToken)
	{
		return null;
	}
}
