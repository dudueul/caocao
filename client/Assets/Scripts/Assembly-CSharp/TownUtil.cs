using UnityEngine;

public static class TownUtil
{
	public enum UpgradeCostType
	{
		LORDLV = 0,
		CASTLE = 1,
		FOOD = 2,
		SILVER = 3,
		GOLD = 4
	}

	public static int GetBuildingLevel(this UserTown userTown, EnumData.TownBuildingType buildingType)
	{
		return 0;
	}

	public static int GetBuildingLookStepLevel(int lev)
	{
		return 0;
	}

	public static int GetFunctionIdByLevel(int funcGroupId, int lev)
	{
		return 0;
	}

	public static int GetFunctionGroupId(int townId, EnumData.TownBuildingType buildingType)
	{
		return 0;
	}

	public static int GetBuildingMaxLevel(int townId, EnumData.TownBuildingType buildingType)
	{
		return 0;
	}

	public static string GetUpgradeEffectString(int funcId, int bonus)
	{
		return null;
	}

	public static int GetBuildingUpgradeCost(UpgradeCostType costType, int funcId)
	{
		return 0;
	}

	public static string GetBuildingName(int townId, EnumData.TownBuildingType buildingType)
	{
		return null;
	}

	public static EnumData.LeaderEffectRange GetLeaderEffectRange(int townId, EnumData.TownBuildingType buildingType)
	{
		return (EnumData.LeaderEffectRange)0;
	}

	public static string GetFarmName(int townId)
	{
		return null;
	}

	public static string GetMarketName(int townId)
	{
		return null;
	}

	public static string GetClipName(int townId, EnumData.TownBuildingType buildingType)
	{
		return null;
	}

	public static EnumData.FoodPlantType GetFoodPlantType(int townId)
	{
		return EnumData.FoodPlantType.FISH;
	}

	public static EnumData.SilverPlantType GetSilverPlantType(int townId)
	{
		return EnumData.SilverPlantType.MARKET;
	}

	public static Color GetTownNameColor(int townId)
	{
		return default(Color);
	}

	public static int GetPlunderableAmount(ulong userId, int townId, EnumData.GoodsType goodType)
	{
		return 0;
	}

	private static int GetPlunderPointEachTown(int townID)
	{
		return 0;
	}

	public static int GetOwnPlunderPoint(int townID, float occapationPct)
	{
		return 0;
	}

	public static int GetOwnResourceAmountByPopularityRecoverType(EnumData.PopularityRecoveryType recoverType)
	{
		return 0;
	}

	public static int GetPopularityUnitIncrease(EnumData.PopularityRecoveryType recoverType)
	{
		return 0;
	}

	public static int GetPopularityUnitCost(EnumData.PopularityRecoveryType recoverType)
	{
		return 0;
	}
}
