using System.Collections.Generic;
using UnityEngine;

public class AchievementFilters : ScriptableObject
{
	public List<EnumData.AchievementCategory> normalAchievements;

	public List<EnumData.AchievementCategory> userStatusAchievements;

	public List<EnumData.AchievementCategory> raidAchievements;

	public List<EnumData.AchievementCategory> vipAchievements;

	private static string ms_assetPath;

	private static AchievementFilters ms_instance;

	public static AchievementFilters instance
	{
		get
		{
			return null;
		}
	}

	public static void Create()
	{
	}
}
