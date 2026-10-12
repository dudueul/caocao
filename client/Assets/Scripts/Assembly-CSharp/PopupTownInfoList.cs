using System.Collections.Generic;
using UnityEngine;

public class PopupTownInfoList : PanelSelfRegister2UIComponentsMonobehaviour
{
	private const int RootPanelDepthValue = 51;

	private const int ScrollViewPanelDepthValue = 55;

	private const int AdjustPanelDepthValue = 1;

	[SerializeField]
	private UIReuseScrollView m_reuseScrollView;

	[SerializeField]
	private UIReuseGrid m_reuseGrid;

	[SerializeField]
	private UIButton[] m_sortBtns;

	[SerializeField]
	private GameObject m_defenseNumObj;

	[SerializeField]
	private UILabelLocalized m_defenseNumLabel;

	private int m_prevControlTownId;

	private EnumData.SortOrder[] initSortOrder;

	private EnumData.TownSortType m_curSortType;

	private EnumData.SortOrder m_curSortOrder;

	private List<UserTown> userTownList;

	private void Start()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateDefenseUI()
	{
	}

	public void UpdateTownInfoItems()
	{
	}

	public void AdjustDepth(bool isAdjust)
	{
	}

	public void OnBtnMayorChange()
	{
	}

	public void OnBtnDefenseDeck()
	{
	}

	public void OnBtnGoToTown()
	{
	}

	private bool IsUnderAttack()
	{
		return false;
	}

	public void OnBtnTownManagement()
	{
	}

	public void OnSortBtnClick()
	{
	}

	private int SortEvent(UserTown x, UserTown y)
	{
		return 0;
	}

	private int MayorLvCompareTo(UserTown x, UserTown y)
	{
		return 0;
	}

	private int BuildingCompareTo(UserTown x, UserTown y, EnumData.TownBuildingType buildingType)
	{
		return 0;
	}

	private void UpdateSortBtn()
	{
	}

	public override void ShowPanel()
	{
	}

	public override void ClosePanel()
	{
	}

	private void Refresh()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	public static void RefreshUI()
	{
	}

	public static void Show()
	{
	}

	public static void Close()
	{
	}

	public static void AdjustPanelDepth(bool isAdjust)
	{
	}

	private static PopupTownInfoList GetInstance()
	{
		return null;
	}

	private void InitControlTown()
	{
	}

	private void UpdateAllCellData()
	{
	}

	public static UserTown GetNextTown(UserTown userTown)
	{
		return null;
	}

	public static UserTown GetPrevTown(UserTown userTown)
	{
		return null;
	}

	public static bool IsEnabled()
	{
		return false;
	}
}
