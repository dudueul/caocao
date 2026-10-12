using System;
using LitJson;
using NPA;

public static class CaocaoToyPushUtil
{
	private static void UpdateLocalPush(CaocaoPushType type)
	{
	}

	private static bool IsNightTime(NPNotificationTime notiTime, int pushType = 0)
	{
		return false;
	}

	private static bool IsValidNotiTime(NPNotificationTime notiTime)
	{
		return false;
	}

	private static NPNotificationTime GetTime2NextPush(CaocaoPushType type)
	{
		return null;
	}

	private static NPNotificationTime GetNPNotificationTimeByDateTime(DateTime time)
	{
		return null;
	}

	private static string GetNotificationString(CaocaoPushType type, params object[] args)
	{
		return null;
	}

	private static string GetMeta(CaocaoPushType type)
	{
		return null;
	}

	private static int GetMaxRemainTime2PlantFull()
	{
		return 0;
	}

	public static void UpdateHarvestFullLocalPush(JsonData response)
	{
	}

	private static TimeSpan GetMaxRemainTime2PopularityReward()
	{
		return default(TimeSpan);
	}

	public static void UpdateFreeTreasureBoxLocalPush()
	{
	}

	public static void UpdateInstanceExpeditionLocalPush()
	{
	}

	public static void UpdateWorldBossRewardEndLocalPush()
	{
	}
}
