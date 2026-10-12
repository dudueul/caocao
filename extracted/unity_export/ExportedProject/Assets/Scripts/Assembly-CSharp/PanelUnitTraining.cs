using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;
using UnityEngine;

public class PanelUnitTraining : SelfRegister2UIComponentsMonobehaviour
{
	private readonly Color32 TxtBlueFontColor;

	private UserUnit m_userUnit;

	public GameObject m_unitNameObj;

	public GameObject m_unitLookObj;

	public PrefabInstance m_unitstatInst;

	public PrefabInstance m_troopstatInst;

	public GameObject m_unitEtcInfoObj;

	public UnitTrainingSlider m_strSlider;

	public UnitTrainingSlider m_wisSlider;

	public UnitTrainingSlider m_chaSlider;

	public UnitTrainingSlider m_dexSlider;

	public UnitTrainingSlider m_luckSlider;

	public UILabelLocalized m_labelStr;

	public UILabelLocalized m_labelWis;

	public UILabelLocalized m_labelCha;

	public UILabelLocalized m_labelDex;

	public UILabelLocalized m_labelLuck;

	public Polygon m_basicStatPolygon;

	public Polygon m_buffStatPolygon;

	private Transform m_trainingBtnUI;

	private Transform m_resetTrainedUnitBtn;

	private Transform m_infoBtn;

	private Transform m_redistributionBtn;

	private GeneralStat m_trainedStat;

	private GeneralStat m_redistributionStat;

	private GeneralStat m_checkStat;

	public UILabelLocalized m_classicInfoLabel;

	public UILabelLocalized m_totalTrainLabel;

	private bool useTicket;

	private ObscuredInt m_cost;

	public Action ResetCallback;

	private bool m_redistributionMode;

	public UserUnit userUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private bool redistributionMode
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	protected void OnDisable()
	{
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

	private void SetCountry()
	{
	}

	private void SetUnitStats()
	{
	}

	private void SetEtcInfo()
	{
	}

	private void SetStatUpItems()
	{
	}

	public bool CanTrainStat(EnumData.UnitStatUpType statType, bool isCharging)
	{
		return false;
	}

	private ObscuredBool IsOverMaxStat(EnumData.UnitStatUpType statType)
	{
		return default(ObscuredBool);
	}

	private ObscuredInt GetTrainingCost(EnumData.UnitStatUpType statType)
	{
		return default(ObscuredInt);
	}

	private ObscuredInt GetWillBeBuyTrainingItemCnt(EnumData.UnitStatUpType statType)
	{
		return default(ObscuredInt);
	}

	private void UpdateCost()
	{
	}

	private void UpdateRedistributionButtonLabel()
	{
	}

	public void ClickUnitLevTraining()
	{
	}

	private void CheckTraining()
	{
	}

	private void CheckResetable()
	{
	}

	private void UpdateRedistributionButton()
	{
	}

	private bool CanRedistributable()
	{
		return false;
	}

	private bool IsNotEnoughStat()
	{
		return false;
	}

	private ObscuredBool IsCheckGoodsCost()
	{
		return default(ObscuredBool);
	}

	private bool IsAvailableResetTrainedUnit()
	{
		return false;
	}

	public void ClickTrainingBtn()
	{
	}

	private void OnRedistributionResponse(JsonData response)
	{
	}

	public void ClickResetBtn()
	{
	}

	private void ProcessBuyTrainingItemEnd(JsonData response)
	{
	}

	private void ProcessTrainingUnit()
	{
	}

	private void OnProcessTrainingUnits(JsonData response)
	{
	}

	public void OnClickResetTrainedUnitBtn()
	{
	}

	public void OnClickRedistributionBtn()
	{
	}

	public void OnResetTrainedUnit()
	{
	}

	public bool ToBeChargeMode(EnumData.UnitStatUpType statType)
	{
		return false;
	}

	public int GetTrainingItemAmount(EnumData.UnitStatUpType statType)
	{
		return 0;
	}

	public int GetTrainableAmount(EnumData.UnitStatUpType statType)
	{
		return 0;
	}

	public void ShowInfoPopup()
	{
	}

	public void OnTrainingSliderUpdated(EnumData.UnitStatUpType statType, int value)
	{
	}

	public void UpdateGraphStats()
	{
	}

	public void UpdateGraphPolygon()
	{
	}

	private void UpdateTotalTrainLabel()
	{
	}

	private void UpdateGraphStatLabel(EnumData.UnitStatUpType statType, int trainedStat)
	{
	}
}
