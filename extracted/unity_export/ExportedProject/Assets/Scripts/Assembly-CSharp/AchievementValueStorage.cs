using System.Collections.Generic;

public class AchievementValueStorage
{
	private Dictionary<EnumData.AchievementCheckType, int> storage;

	public void AddValue(EnumData.AchievementCheckType type, int addValue = 1)
	{
	}

	public void SetValue(EnumData.AchievementCheckType type, int setValue)
	{
	}

	public int GetValue(EnumData.AchievementCheckType type, bool isReset = true)
	{
		return 0;
	}

	public void Clear()
	{
	}
}
