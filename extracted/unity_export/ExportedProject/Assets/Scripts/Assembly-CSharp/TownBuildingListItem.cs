using System;
using System.Collections.Generic;
using UnityEngine;

public class TownBuildingListItem : MonoBehaviour
{
	private UserTown m_userTown;

	public EnumData.TownBuildingType m_buildingType;

	private int m_targetLevel;

	private int m_targetCastleLevel;

	private Dictionary<EnumData.GoodsType, int> m_costs;

	public Action OnTargetCostChanged;

	public Action<EnumData.TownBuildingType, int> OnTargetLevelChanged;

	public UILabelLocalized infoBuildingName;

	public UISprite infoBuildingIcon;

	public UILabelLocalized gaugeDesc;

	public UILabelLocalized gaugeValue;

	public UISprite gaugeBar;

	public UISprite gaugeBarDelta;

	public UILabelLocalized sliderNextLevel;

	public UISlider slider;

	public UISprite sliderCurrent;

	public UISprite sliderMaxTarget;

	public PressableButton sliderPlusBtn;

	public PressableButton sliderMinusBtn;

	public GameObject sliderControl;

	public GameObject maxLevelDesc;

	private TownFunctionGroup functionGroup;

	private TownFunction townFunc;

	private TownFunction lastFunc;

	private TownFunction targetFunc;

	private const int str1st = 9000479;

	private UserTown userTown
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private int buildingLevel
	{
		get
		{
			return 0;
		}
	}

	private int buildingMaxLevel
	{
		get
		{
			return 0;
		}
	}

	private int targetLevel
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	private int targetCastleLevel
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int GetCost(EnumData.GoodsType goodsType)
	{
		return 0;
	}

	public void SetUserTown(UserTown userTown, int defaultTargetLev, int targetCastleLev)
	{
	}

	private void SetTownFuncs()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateInfoUI()
	{
	}

	private void UpdateGuageUI()
	{
	}

	private void UpdateSliderUI()
	{
	}

	private void UpdateTargetCalculatedCost()
	{
	}

	private void OnSliderUpdated()
	{
	}

	public void OnPlusBtn()
	{
	}

	public void OnPlusBtnImpl(int count)
	{
	}

	public void OnMinusBtn()
	{
	}

	public void OnMinusBtnImpl(int count)
	{
	}

	public void OnUpgradeCompleted()
	{
	}

	private void OnDisable()
	{
	}

	private void OnEnable()
	{
	}
}
