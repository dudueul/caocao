using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PanelAchievement : MonoBehaviour
{
	private const int MAX_ITEM_COUNT = 3;

	private EnumData.AchievementCategory m_category;

	private bool m_isHideComplete;

	private void Start()
	{
	}

	public void ShowPanel()
	{
	}

	public void OnClickCategoryAll()
	{
	}

	public void OnClickCategoryCommon()
	{
	}

	public void OnClickCategoryBattle()
	{
	}

	public void OnClickCategoryScenario()
	{
	}

	public void OnClickCategoryInternal()
	{
	}

	public void OnClickGoogleAchieve()
	{
	}

	public void OnHideComplete()
	{
	}

	public void OnShowAll()
	{
	}

	private void UpdateCategory(EnumData.AchievementCategory category)
	{
	}

	private void UpdateShowHide(bool isHide)
	{
	}

	private void Init()
	{
	}

	private void UpdateAchievements(UIReuseGrid grid, EnumData.AchievementCategory category)
	{
	}

	private void AddGridListItem(UIReuseGrid grid, List<UserAchievement> list)
	{
	}

	private Comparison<UserAchievement> SortAchievements()
	{
		return null;
	}

	private void UpdateTabs()
	{
	}

	private void UpdateGPGS()
	{
	}

	private void _UpdateTabs(Transform tf, bool activity)
	{
	}

	private void UpdateGauge(int gauge, int total)
	{
	}

	private void UpdateComplete(Achievement achievement, JsonData response, bool treasure)
	{
	}

	private void ShowComplete(Achievement achievement, List<RewardInfo> rewardInfos, bool treasure)
	{
	}

	private void OnCompleteClose()
	{
	}

	private void ClearGrid()
	{
	}

	public void ClosePanel()
	{
	}

	private Transform GetGridTransform()
	{
		return null;
	}

	private UIReuseGrid GetGrid()
	{
		return null;
	}

	private void SetActivityCount(int count)
	{
	}
}
