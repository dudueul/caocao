using System;
using System.Collections.Generic;

public static class TimeBoxUtil
{
	public enum ExpiryType
	{
		Day = 0,
		Week = 1,
		Month = 2,
		Never = 3
	}

	public static int seasonId
	{
		get
		{
			return 0;
		}
	}

	public static int GetOpenBoxImmediateCost(TimeSpan remainTime)
	{
		return 0;
	}

	public static List<int> GetSpecialSkinIdsInSeason()
	{
		return null;
	}

	public static TimeBoxDecide GetReplaceItem(int season, TimeBoxDecide decide)
	{
		return default(TimeBoxDecide);
	}

	public static Season GetSeason()
	{
		return null;
	}

	public static string GetExpiryDateTimeLeftString(ExpiryType expiryType)
	{
		return null;
	}
}
