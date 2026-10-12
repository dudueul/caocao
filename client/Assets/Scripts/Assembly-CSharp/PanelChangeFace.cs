using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class PanelChangeFace : CaocaoUIModalPopup
{
	private enum ChangeFaceType
	{
		USER = 0
	}

	private enum ChangeFaceSortFilter
	{
		[LocalizedDesc]
		NAME = 0
	}

	public UIButton m_btnChange;

	public UIButton m_btnCancel;

	public UIButton m_btnSort;

	public UIScrollView m_unitScrollView;

	public SkinItem m_currentFace;

	public SkinItem m_targetFace;

	private UnitListItem m_selectedUnit;

	private EnumData.SortOrder m_sortOrder;

	private ChangeFaceSortFilter m_sortingFilter;

	private ChangeFaceType m_changeFaceType;

	protected override void Awake()
	{
	}

	protected override void OnDestroy()
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

	public static void ShowChanageLeaderFace()
	{
	}

	private static void ShowChangeFace(ChangeFaceType type)
	{
	}

	private void OnClosePanel()
	{
	}

	private static IList<int> GetSortedUnitList(ChangeFaceType type, EnumData.SortOrder sortOrder, ChangeFaceSortFilter filterType = ChangeFaceSortFilter.NAME)
	{
		return null;
	}

	private static IList<int> GetUnitListOrderedByName(ChangeFaceType type, EnumData.SortOrder sortOrder)
	{
		return null;
	}

	private static void AddUserUnits(SortedList<string, int> list, IEnumerable<UserUnit> userUnits)
	{
	}

	[DebuggerHidden]
	private IEnumerator UpdateUnitListCoroutine()
	{
		return null;
	}

	private void UpdateUnitList(IList<int> orderdUnitList, int selectedUnitId)
	{
	}

	private void UpdateFaceList(SkinItem item, int unitId, int skinItemId, int skinUnitId)
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateUI(IList<int> unitList)
	{
	}

	private void SelectUnit(UnitListItem item)
	{
	}

	[DebuggerHidden]
	private IEnumerator RemoveAllUnit()
	{
		return null;
	}

	private void RemoveGrid(Transform grid)
	{
	}

	public void OnClickSortOrder()
	{
	}

	public void OnClickChangeFace()
	{
	}

	public void OnClickCanel()
	{
	}

	private void OnClickChangeUserFace()
	{
	}

	private void OnCompleteChangeUserFace(JsonData response)
	{
	}
}
