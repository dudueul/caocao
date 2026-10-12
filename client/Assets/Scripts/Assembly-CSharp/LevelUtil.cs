using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class LevelUtil
{
	private static List<ObscuredInt> ms_levelKeys;

	private static List<ObscuredInt> rpeIds;

	private static bool Search(int exp, int need, int sum, int searchingIdx, ref int endIdx, ref int startIdx)
	{
		return false;
	}

	public static int GetUserLevel(int exp)
	{
		return 0;
	}

	public static int GetUnitLevel(int exp)
	{
		return 0;
	}

	public static int GetWeaponLevel(int exp)
	{
		return 0;
	}

	public static int GetArmorLevel(int exp)
	{
		return 0;
	}

	public static int GetVipLevel(int point)
	{
		return 0;
	}

	public static int GetGuildLevel(int exp)
	{
		return 0;
	}

	public static int GetGuildNeedExp(int level)
	{
		return 0;
	}

	public static int GetGuildExpSum(int level)
	{
		return 0;
	}

	public static int GetUserExpSum(int lev)
	{
		return 0;
	}

	public static int GetUnitExpSum(int lev)
	{
		return 0;
	}

	public static int GetWeaponExpSum(int lev)
	{
		return 0;
	}

	public static int GetArmorExpSum(int lev)
	{
		return 0;
	}

	public static int GetJobLevelFromUnitLevel(int jobTypeId, int unitLevel)
	{
		return 0;
	}

	public static int GetMaxLevel(int jobType)
	{
		return 0;
	}

	public static int GetMaxLevel(JobType jobType, int jobLevel)
	{
		return 0;
	}

	public static int GetMaxJobLevel(JobType jobType)
	{
		return 0;
	}

	public static int GetMaxReinforceLevel(int jobType)
	{
		return 0;
	}

	public static int GetMaxSoldierLevel(ulong uid)
	{
		return 0;
	}

	public static int GetLimitedSoldierLevel(ulong uid, int userLevel)
	{
		return 0;
	}

	public static int GetSoldierReinforce(int level, int jobTypeId)
	{
		return 0;
	}

	public static int GetGuildFacilityLevel(int facilityId, int exp)
	{
		return 0;
	}

	public static void GetGuildSiegeLevelUpInfo(int facilityId, int curExp, out int lv, out int needExp, out int expSum, out int needGuildLv)
	{
		lv = default(int);
		needExp = default(int);
		expSum = default(int);
		needGuildLv = default(int);
	}

	public static int GetGuildSiegeMaxLevelByGuildLevel(int facilityId, int guildLv)
	{
		return 0;
	}

	public static int GetRunePassiveLevel(int exp, int tier)
	{
		return 0;
	}

	public static int GetRuneMaxLevel(int tier)
	{
		return 0;
	}

	public static RunePassiveExp GetCurrentRuneExpPd(int exp, int tier)
	{
		return null;
	}

	public static int GetRunePassiveExp(int level, int tier)
	{
		return 0;
	}
}
