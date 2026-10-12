using System.Collections.Generic;

internal class ConditionUtil
{
	private static List<EnumData.Conditions> ms_badConditionIDs;

	private static IList<EnumData.Conditions> ms_readOnlyBadConditionList;

	private static Dictionary<EnumData.ConditionType, IList<EnumData.Conditions>> ms_readOnlyConditionIDsByType;

	private static List<EnumData.Conditions> ms_goodConditionIDs;

	private static IList<EnumData.Conditions> ms_readOnlyGoodConditionList;

	public static EnumData.Conditions GetRandStatDownCondition()
	{
		return EnumData.Conditions.NONE;
	}

	public static EnumData.Conditions GetRandAbnormalCondition()
	{
		return EnumData.Conditions.NONE;
	}

	public static EnumData.Conditions GetRandGoodCondition()
	{
		return EnumData.Conditions.NONE;
	}

	public static IList<EnumData.Conditions> GetDebuffConfitionIDs()
	{
		return null;
	}

	public static IList<EnumData.Conditions> GetConditionIDsByType(EnumData.ConditionType conditionType)
	{
		return null;
	}

	public static bool IsDebuffCondition(EnumData.Conditions condition)
	{
		return false;
	}

	public static IList<EnumData.Conditions> GetBuffConfitionIDs()
	{
		return null;
	}

	public static bool IsBuffCondition(EnumData.Conditions condition)
	{
		return false;
	}

	public static int GetHierarchyIndex(EnumData.Conditions condition)
	{
		return 0;
	}

	public static EnumData.Conditions GetConditionByHierachyIndex(EnumData.Conditions conditionId, int hierarchyIndex)
	{
		return EnumData.Conditions.NONE;
	}

	public static int GetHighHierarchyConditionId(EnumData.Conditions condition)
	{
		return 0;
	}

	public static int GetLowHierarchyConditionId(EnumData.Conditions condition)
	{
		return 0;
	}

	public static bool IgnoreReflected(EnumData.Conditions condition)
	{
		return false;
	}

	public static bool IsStackableCondition(EnumData.Conditions conditionId)
	{
		return false;
	}

	public static int GetStackConditionValue(EnumData.Conditions conditionid, int stackCount)
	{
		return 0;
	}
}
