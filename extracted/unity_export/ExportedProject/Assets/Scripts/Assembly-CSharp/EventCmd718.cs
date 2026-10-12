using System;
using System.Xml;

[Serializable]
public class EventCmd718 : EventCmdBase
{
	public static readonly string ms_code;

	public bool checkCountry;

	public int country;

	public bool checkLordRank;

	public int lordRank;

	public EnumData.CompareSign lordRankCalSignIdx;

	public bool checkUnitLv;

	public int unitLv;

	public bool canPromote;

	public bool canReinforce;

	public EnumData.CompareSign unitLvCalSignIdx;

	public bool checkIsItemMaxLv;

	public bool checkIsAvailableItemUpgrde;

	public bool checkItemExist;

	public int itemID;

	public int itemCnt;

	public EnumData.CompareSign itemCntCalSignIdx;

	public bool checkConsumableItemExist;

	public bool checkUnitExist;

	public int unitID;

	public bool isExistUnit;

	public bool checkTechLv;

	public int techID;

	public int techLv;

	public EnumData.CompareSign techLvCalSignIdx;

	public bool checkCMComplete;

	public int commonMissionID;

	public bool checkScenario;

	public int scenarioID;

	public bool isHardMode;

	public bool isScenarioWin;

	public bool checkInstanceWinCnt;

	public int instanceID;

	public int instanceWinCnt;

	public EnumData.CompareSign instanceWinCalSignIdx;

	public bool checkTownCnt;

	public int townCnt;

	public EnumData.CompareSign townCntCalSignIdx;

	public bool checkUnequppedItem;

	public int unequippedItemID;

	public bool checkTownOwned;

	public int townId;

	public bool checkTownNotOwned;

	public int notOwnedTownId;

	public bool checkHarvestRate;

	public bool checkSilverRate;

	public bool checkFoodRate;

	public int harvestTownId;

	public int silverRate;

	public int foodRate;

	public EnumData.CompareSign silverRateCalSignIdx;

	public EnumData.CompareSign foodRateCalSignIdx;

	public bool checkTownBuildingLv;

	public EnumData.TownBuildingType townBuildingType;

	public int townBuildingLv;

	public EnumData.CompareSign townBuildingLvCalSignIdx;

	public bool checkCloudOpen;

	public int cloudId;

	public bool isCloudOpened;

	private EnumData.ItemSlotType[] checkMaxLevelSlotTypes;

	private EnumData.ItemSlotType[] checkUpgradableSlotTypes;

	public bool checkBattleMode;

	public EnumData.BattleMode battleMode;

	public bool checkTownIdInTownScene;

	public int townIdInTownScene;

	public bool checkGuildExist;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
