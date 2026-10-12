using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class PanelSelectMayor : CaocaoUIModalPopup
{
	private Color Gradient1TopColor;

	private Color Gradient1BottomColor;

	public UILabelLocalized m_panelTitleUI;

	private UnitCommonProfile m_unitProfile;

	public Transform m_nameJobUI;

	public UIScrollView m_scrollView;

	private Transform m_selected;

	private ulong m_selectedSrl;

	private EnumData.ChangeMayorSortFilter m_curSortType;

	private EnumData.SortOrder m_sortOrder;

	private bool m_sendRequest;

	private bool m_started;

	private int m_tempMayorUnitId;

	private int m_tempMayorUnitTownId;

	private int m_tempMayorUnitBfIdx;

	private UILabelLocalized m_sortLabel;

	private void Start()
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

	public void Init()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void UpdateDetailUI()
	{
	}

	private void UpdateSortButtonUI()
	{
	}

	public void RecreateUI(int tempMayorUnitId = 0, int tempMayorUnitTownId = 0, int tempMayorUnitBfIdx = -1)
	{
	}

	public void Close()
	{
	}

	public void ShowInfoMsg()
	{
	}

	public void SetMayor()
	{
	}

	[DebuggerHidden]
	private IEnumerator ChangeScene(string sceneName)
	{
		return null;
	}

	private void CreateUnitList()
	{
	}

	[DebuggerHidden]
	private IEnumerator RemoveAllUnit()
	{
		return null;
	}

	public void ShowSortingList()
	{
	}

	public void SortOrder(Enum sortType)
	{
	}

	[DebuggerHidden]
	private IEnumerator UpdateGridCoroutine()
	{
		return null;
	}

	private List<UserUnit> GetOrderedUnits(EnumData.ChangeMayorSortFilter sortType)
	{
		return null;
	}

	private void SelectUnit(Transform item, ulong srl)
	{
	}

	private void OnCompleteSetMayor(JsonData response)
	{
	}

	private int SortEvent(UserUnit x, UserUnit y)
	{
		return 0;
	}
}
