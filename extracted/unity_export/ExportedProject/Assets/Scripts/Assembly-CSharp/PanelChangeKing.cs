using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class PanelChangeKing : CaocaoUIModalPopup
{
	private enum CHANGE_UNIT_TYPE
	{
		King = 0,
		Mayor = 1
	}

	private enum SortWeightValue
	{

	}

	private Color Gradient1TopColor;

	private Color Gradient1BottomColor;

	private UILabelLocalized m_panelTitleUI;

	private Transform m_leftUnitUI;

	private Transform m_rightUnitUI;

	private UIScrollView m_scrollView;

	private UILabelLocalized m_costFoodPerTimeRightUnitUI;

	private UILabelLocalized m_costFoodChangeLordUI;

	private Transform m_selected;

	private ulong m_selectedSrl;

	private int m_beforeUnitId;

	private EnumData.ChangeMayorSortFilter m_sortingFilter;

	private EnumData.SortOrder m_sortOrder;

	private CHANGE_UNIT_TYPE m_changeUnitType;

	private GameObject m_kingChangeFxObj;

	private bool isChangeKingMode
	{
		get
		{
			return false;
		}
	}

	private bool isChangeMayorMode
	{
		get
		{
			return false;
		}
	}

	protected override void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void Init()
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

	public void ShowProfile(bool show)
	{
	}

	private void CreateUnitList()
	{
	}

	public static void ShowChangeKing()
	{
	}

	public static void ShowChangeMayor()
	{
	}

	private static void ShowPanelImpl(CHANGE_UNIT_TYPE type)
	{
	}

	private void ShowChangeKingPanel(CHANGE_UNIT_TYPE type)
	{
	}

	private void UpdatePanel()
	{
	}

	private void UpdateUnitInfo(Transform root, UserUnit userUnit, UserTown userTown)
	{
	}

	private void OnClosePanel()
	{
	}

	[DebuggerHidden]
	private IEnumerator RemoveAllUnit()
	{
		return null;
	}

	private void SelectUnit(Transform item, ulong srl)
	{
	}

	private void SetCostFoodLabel(int cost)
	{
	}

	private int GetCostChangeUnit(CHANGE_UNIT_TYPE unitType, ulong unitSrl)
	{
		return 0;
	}

	private void SetCostFoodPerTimeByUnit(UILabelLocalized targetUI, ulong unitSrl)
	{
	}

	public void ShowSortingList()
	{
	}

	public void SortOrder(Enum selected)
	{
	}

	[DebuggerHidden]
	private IEnumerator UpdateGridCoroutine()
	{
		return null;
	}

	private List<UserUnit> GetOrderedUnits(UserUnits userUnits, EnumData.ChangeMayorSortFilter filterType)
	{
		return null;
	}

	private List<UserUnit> GetOrderedUnitsByMayorMode(UserUnits userUnits, EnumData.ChangeMayorSortFilter filterType)
	{
		return null;
	}

	public void SetKing()
	{
	}

	private void OnCompleteSetKing(JsonData response)
	{
	}

	private void OnCompleteSetMayor(JsonData response)
	{
	}

	private void OnCompleteSetMayorAfterSetKing(JsonData response)
	{
	}

	private void OnCompleteSetKingAfterSetMayor(JsonData response)
	{
	}

	public void OnKingChanged()
	{
	}

	private int SortEvent(UserUnit x, UserUnit y)
	{
		return 0;
	}

	private string GetSortTypeName()
	{
		return null;
	}
}
