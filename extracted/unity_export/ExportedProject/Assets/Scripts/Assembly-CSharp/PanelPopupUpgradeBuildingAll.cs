using System.Collections.Generic;
using System.Text;
using LitJson;

public class PanelPopupUpgradeBuildingAll : CaocaoUIModalPopup
{
	private UserTown m_userTown;

	private UserTown m_nextTown;

	private UserTown m_prevTown;

	public UILabelLocalized m_townName;

	public TownBuildingListItem m_castle;

	public TownBuildingListItem m_cashStorage;

	public TownBuildingListItem m_foodStorage;

	public TownBuildingListItem m_silverStorage;

	public TownBuildingListItem m_foodPlants;

	public TownBuildingListItem m_silverPlants;

	public UILabelLocalized m_foodCost;

	public UILabelLocalized m_silverCost;

	public UIButton m_nextPage;

	public UIButton m_prevPage;

	public UILabelLocalized m_nextName;

	public UILabelLocalized m_prevName;

	private Dictionary<EnumData.GoodsType, int> costs;

	private static Dictionary<EnumData.TownBuildingType, int> targetLevelByBuildingType;

	private static Dictionary<EnumData.TownBuildingType, int> lastUpdatedTargetLevels;

	private bool m_hideInfoList;

	private int prevUserLevel;

	private StringBuilder sb;

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

	protected override void Awake()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
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

	public int GetTargetLevel(EnumData.TownBuildingType buildingType, bool withBckupLv = false)
	{
		return 0;
	}

	public static void Show(UserTown userTown, bool hideInfoList = false)
	{
	}

	private void SetPrevNextTown()
	{
	}

	public void SetUserTown(UserTown userTown)
	{
	}

	public void OnPrevPageClick()
	{
	}

	public void OnNextPageClick()
	{
	}

	private void UpdateUI(bool withBckupLv)
	{
	}

	private void UpdateTotalCost()
	{
	}

	private void UpdateCostButton()
	{
	}

	public void OnUpgradeBtnClick()
	{
	}

	public void UpdateTargetLevel(EnumData.TownBuildingType buildingType, int targetLevel)
	{
	}

	public void OnUpgradeTownBuildings(JsonData response)
	{
	}

	private void CheckCommonMissionForAllUpgrade()
	{
	}

	private void CheckCommonMissions(EnumData.TownBuildingType buildingType)
	{
	}
}
