using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class PopupSkinItemInfo : CaocaoUIModalPopup
{
	public enum ChangeType
	{
		Face = 0,
		Skin = 1
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

	public Transform m_faceList;

	public UIScrollView m_faceScrollView;

	public Transform m_unitSpriteList;

	public UIScrollView m_unitSpriteScrollView;

	public Transform m_faceScrollDim;

	public Transform m_unitSpriteScrollDim;

	public PrefabInstance m_defaultSkinItem;

	public SkinItem m_defaultSkinUnitSpriteItem;

	public Transform m_topInfoSkin;

	public Transform m_topInfoFace;

	public Transform m_currentFace;

	public Transform m_targetFace;

	private UserUnit m_userUnit;

	private ChangeType changeType;

	private SkinItemUnitList m_selectedUnit;

	private SkinItem m_selectedUnitSkin;

	private EnumData.SortOrder m_sortOrder;

	private ChangeFaceSortFilter m_sortingFilter;

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

	public static void ShowChangeUnitSkin(UserUnit userUnit)
	{
	}

	public static void ShowChangeKingFace()
	{
	}

	private void OnClosePanel()
	{
	}

	private static IList<int> GetSortedKingFaceUnitList(EnumData.SortOrder sortOrder)
	{
		return null;
	}

	private static IList<int> GetSortedSkinUnitList(EnumData.SortOrder sortOrder)
	{
		return null;
	}

	private static void AddUserUnitsForFace(SortedList<string, int> list)
	{
	}

	private static void AddUserUnitsForSkin(SortedList<string, int> list)
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

	private void UpdateFaceList(int unitID)
	{
	}

	private Transform GetNewSkinFaceItem(int unitId, int skinItemId, int selectUnitSkinId)
	{
		return null;
	}

	private Transform GetNewSkinUnitSpriteItem(UserUnit userUnit, int skinItemId, int selectUnitSkinId)
	{
		return null;
	}

	private void UpdateUI()
	{
	}

	private void UpdateFaceUI(IList<int> unitList, int kingUnit)
	{
	}

	private void UpdateSkinUI(IList<int> unitList, int selectUnitId)
	{
	}

	private void SelectUnit(SkinItemUnitList item)
	{
	}

	private void SelectUnitSkin(SkinItem item)
	{
	}

	private void UpdateFaceLook(int unitId, int skinItemId)
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

	public void OnRotateLeft()
	{
	}

	public void OnRotateRight()
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

	private void OnClickChangeUnitFace()
	{
	}

	private void UpdateChangeButton()
	{
	}

	private void OnCompleteChangeUnitFace(JsonData response)
	{
	}
}
