using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class PanelUnitPromotion : SelfRegister2UIComponentsMonobehaviour
{
	private UserUnit m_userUnit;

	public GameObject m_unitNameObj;

	public GameObject m_curUnitLookObj;

	public GameObject m_nextUnitLookObj;

	public GameObject m_completeUnitLookObj;

	public UILabelLocalized m_curUnitJobNameUI;

	public UILabelLocalized m_nextUnitJobNameUI;

	public UILabelLocalized m_completeUnitJobNameUI;

	public Transform m_curBenefitListTr;

	public Transform m_nextBenefitListTr;

	public UILabelLocalized m_necessaryCnt;

	public Transform m_scrollView;

	public Transform m_BtnExcute;

	public Transform m_goodsCostBtnTr;

	private List<float> m_necessaryPcts;

	private bool m_isProcessing;

	private int maxReinforceLevel;

	private List<Tuple<int, int, EnumData.ItemSlotType>> m_needCraftItemList;

	private List<Tuple<EnumData.ItemSlotType, int>> m_upgradeUnitItemList;

	private List<int> m_needReinforceLevelList;

	private int stampCost;

	private int needUnitMaxLevel;

	private UserUnit userUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool isProcessing
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public void ShowPanel(bool show, UserUnit userUnit)
	{
	}

	public void ClosePane()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateUnitName()
	{
	}

	private void UpdateUnitLook()
	{
	}

	private void UpdateUnitJobLevStar()
	{
	}

	private void UpdateUnitJobName()
	{
	}

	private void UpdatePromotionBenefit()
	{
	}

	private void _UpdateBenefitInfo(List<UILabelLocalized> benefitLabelUIs, int jobLev)
	{
	}

	private void UpdateNecessary()
	{
	}

	private void SetNecessaryInfo(GameObject obj, int idx, UnitJobLv unitJobLv, out float pct)
	{
		pct = default(float);
	}

	private string GetIconName(UnitJobLv unitJobLv, int idx)
	{
		return null;
	}

	private void SetNecessaryCount()
	{
	}

	private void UpdateGoodsCost()
	{
	}

	private void CheckPromotion()
	{
	}

	private bool IsCheckNecessary()
	{
		return false;
	}

	[DebuggerHidden]
	private IEnumerator PromotionEffect()
	{
		return null;
	}

	private void OnProcessPromotionUnit(JsonData response)
	{
	}

	public void AutoPromotionUnit()
	{
	}

	private bool CheckAllItemCanUpgrade()
	{
		return false;
	}

	private void ExecuteAutoUnitPromotion()
	{
	}

	private void ShowAutoItemMixPopup(Dictionary<int, int> stuffs, int cost)
	{
	}

	protected int GetLevelUpCost(int toLevel)
	{
		return 0;
	}

	private void CraftItem(Tuple<int, int, EnumData.ItemSlotType> item)
	{
	}

	private void UpgradeUnitItem(Tuple<EnumData.ItemSlotType, int> item)
	{
	}

	private void RequestUnitLevPlus(int targetLevel, int cost)
	{
	}

	private void ReinforceUnit()
	{
	}

	private void PromoteUnit()
	{
	}
}
