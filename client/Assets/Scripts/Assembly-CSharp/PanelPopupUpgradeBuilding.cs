using System;
using LitJson;
using UnityEngine;

public class PanelPopupUpgradeBuilding : CaocaoUIModalPopup
{
	public UILabelLocalized m_titleUI;

	public UILabelLocalized m_currentLevel;

	public UILabelLocalized m_effectUI1;

	public UILabelLocalized m_effectUI2;

	public UILabelLocalized m_effectUI3;

	public UILabelLocalized m_needFoodUI;

	public UILabelLocalized m_needSilverUI;

	public GameObject m_goodsObj;

	public UILabelLocalized m_consumeGoodsUI;

	public GameObject m_btnObj;

	public UILabelLocalized m_descriptionUI;

	public GameObject m_lockObj;

	public GameObject m_btnCancelObj;

	public GameObject m_sliderUI1;

	public UISprite m_iconUI;

	public UILabelLocalized deltaValueUI1;

	public EnumData.TownBuildingType m_townBuildingType;

	public GameObject m_prevBtn;

	public GameObject m_nextBtn;

	private Town m_town;

	private UserTown m_myTown;

	private AudioSource m_SE_AudioSource;

	private int m_prevLevel;

	private Action<EnumData.TownBuildingType> onUpgradeCallback__BackingField;

	public bool m_byShortcut;

	private int m_townId;

	public event Action<EnumData.TownBuildingType> onUpgradeCallback
	{
		add
		{
		}
		remove
		{
		}
	}

	protected override void Awake()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	private void Initialize()
	{
	}

	public void NextTownBuilding()
	{
	}

	public void PrevTownBuilding()
	{
	}

	private void UpdateDetailInfo(EnumData.TownBuildingType buildingType)
	{
	}

	private void UpdateEffect1UIDetail(EnumData.TownBuildingType buildingType, int townFuncGroupId)
	{
	}

	private void UpdateEffect2UIDetail(int townFuncId)
	{
	}

	private void UpdateEffect3UIDetail(EnumData.TownBuildingType buildingType, int curLevel, int maxLevel)
	{
	}

	private void UpdateEffectUIPosition()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public void CheckUpgradeBuilding()
	{
	}

	private void UpgradeBuilding(bool isByGold, int townId, EnumData.TownBuildingType buildingType)
	{
	}

	private void OnUpgradeBuilding(JsonData response)
	{
	}

	private void CheckCommonMissions(EnumData.TownBuildingType buildingType)
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}
}
