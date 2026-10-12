using System;

public class PopupTownMenu : SelfRegister2UIComponentsMonobehaviour
{
	private UserTown m_userTown;

	public UILabelLocalized m_townNameUI;

	public UILabelLocalized m_townGradeUI;

	public UILabelLocalized m_townMayorUnitUI;

	public UILabelLocalized m_townInnerUI;

	public UILabelLocalized m_townFarmUI;

	public UILabelLocalized m_townMarketUI;

	public UILabelLocalized m_townFoodStorageUI;

	public UILabelLocalized m_townSilverStorageUI;

	public UILabelLocalized m_townSafeUI;

	public UILabelLocalized m_plunderSilverUI;

	public UILabelLocalized m_plunderFoodUI;

	public UILabelLocalized m_plunderTitleUI;

	public Action OnSetDefenseDeck;

	public Action OnClosePopup;

	private void Start()
	{
	}

	private void Update()
	{
	}

	public void ClosePopup()
	{
	}

	public void GoIntoTown()
	{
	}

	public void ReconTown()
	{
	}

	public void RefreshUI()
	{
	}

	private void SetDetailInfo(int townId)
	{
	}

	private void SetBuildingInfo(UILabelLocalized targetUI, int townId, EnumData.TownBuildingType buildingType)
	{
	}

	private void SetButtons(int townId)
	{
	}

	public void SetHarvestButton(int townId)
	{
	}

	public void SetDefenseButton(int townId)
	{
	}

	public void HarvestTownGoods()
	{
	}

	public void SetDefenceUnitDeck()
	{
	}

	private void SetShamBattleBtn(int townId)
	{
	}

	private void SetMoveCaptialButton(int townId)
	{
	}

	public void ClickShamBattle()
	{
	}
}
