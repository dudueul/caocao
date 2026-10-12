using System.Collections.Generic;
using LitJson;

public class CommonMissionsDistribution : Singleton<CommonMissionsDistribution>
{
	private const float requestInterval = 1f;

	private float missionElapsedTime;

	private float achieveElapsedTime;

	private bool missionHold;

	private bool achieveHold;

	private List<int> requestedMissions;

	private List<int> requestedAchieves;

	private List<UnitData> defeatUnits;

	private void Update()
	{
	}

	public void RegesterResponses()
	{
	}

	private void OnGetUnit(JsonData resoponse)
	{
	}

	private void OnGetUnitPurchase(JsonData resoponse)
	{
	}

	private void OnRefreshStoreItemList(JsonData resoponse)
	{
	}

	private void OnEquipItem(JsonData resoponse)
	{
	}

	public void CheckMissions()
	{
	}

	private void CommonMissionUpdate()
	{
	}

	private void AchievementUpdate()
	{
	}

	private void OnCommonMissionUpdate(JsonData response)
	{
	}

	private void OnAchievementUpdate(JsonData reponse)
	{
	}

	public void CheckGetUnit(List<int> addUnits)
	{
	}

	public void CheckUnitMIssions()
	{
	}

	public void CheckUnitTraining(int count)
	{
	}

	public void CheckUnitPassive()
	{
	}

	public void CheckGoods(ulong uid, int prev, int cur, EnumData.GoodsType type)
	{
	}

	public void CheckSaveFlag(List<UserSaveFlag> flags)
	{
	}

	public void CheckItem(List<UserItem> items)
	{
	}

	public void CheckUnitUpgrade(int amount = 1)
	{
	}

	public void CheckUserLevelUp(ulong uid, int level)
	{
	}

	public void CheckUpdateMission()
	{
	}

	public void CheckCommonMissionComplete(int missionId)
	{
	}

	public void CheckAllCommonMissionsAndAchievements()
	{
	}

	private void CheckCommonMissionManual()
	{
	}

	private void CheckAchievementManual()
	{
	}

	public void CheckSpecialAchievements()
	{
	}

	public void ApplyUnitDefeatInfo(UnitData defeatUnit)
	{
	}

	public void RevertUnitDefeatInfo(UnitData defeatUnit)
	{
	}

	public void ClearDefeatUnits()
	{
	}

	public void CheckDefeatUnits()
	{
	}

	private void _CheckCommonMissionManual(EnumData.CommonMissionCheckType checkType)
	{
	}

	private void CheckEachMission(EnumData.CommonMissionCheckType checkType, List<CommonMission> missions)
	{
	}

	private void CheckMissionOnUnit(EnumData.CommonMissionCheckType checkType, List<CommonMission> missions)
	{
	}

	private void CheckMissionOnArea(EnumData.CommonMissionCheckType checkType)
	{
	}

	private void CheckMissionOnMap(EnumData.CommonMissionCheckType checkType, List<CommonMission> missions)
	{
	}

	private void CheckMissionOnTownOwner(EnumData.CommonMissionCheckType checkType, List<CommonMission> missions)
	{
	}

	private void CheckMissionOnBuildingUpgrade(EnumData.CommonMissionCheckType checkType, List<CommonMission> missions)
	{
	}

	private void CheckMissionOnState(EnumData.CommonMissionCheckType checkType, List<CommonMission> missions)
	{
	}

	private void CheckMissionOnTownBuildings(EnumData.CommonMissionCheckType checkType, List<CommonMission> missions)
	{
	}

	private void CheckMissionOnTotalComplete(EnumData.CommonMissionCheckType checkType, List<CommonMission> missions)
	{
	}

	private void CheckMissionOnLogin(EnumData.CommonMissionCheckType checkType, List<CommonMission> missions)
	{
	}

	private void CheckMissionOnUserTech(EnumData.CommonMissionCheckType checkType, List<CommonMission> missions)
	{
	}

	private void CheckMissionOnUnitPromotion(EnumData.CommonMissionCheckType checkType, List<CommonMission> missions)
	{
	}

	private int _CheckGetUnit(int setId)
	{
		return 0;
	}

	private int _CheckUnitTraining(int setId)
	{
		return 0;
	}

	private void CheckTreeUnitsMaxValue(int setId, ref bool maxExp, ref bool maxGrade, ref bool maxReinforce, ref int totalUnitCnt)
	{
	}

	private int _CheckUnitPassive(int setId)
	{
		return 0;
	}
}
