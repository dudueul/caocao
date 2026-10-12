using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class PanelTownList : SelfRegister2UIComponentsMonobehaviour
{
	private Color m_colorRed;

	public UIButton m_btnRegionName;

	public UIButton m_btnTownName;

	public UIButton m_btnStatus;

	public UIButton m_btnMayor;

	public UIButton m_btnPopularity;

	private GameObject m_harvestAll;

	private Transform m_scrollView;

	private Transform m_grid;

	public static WorldMapControlFlow.sortType m_curSortingOrder;

	public static bool m_isDescend;

	private int m_focusedTownID;

	private Dictionary<int, GameObject> m_townId2GameObjs;

	public static Action<bool> OnTownListToggled;

	public static Action<int> OnTownListItemClicked;

	private bool m_started;

	protected override void Awake()
	{
	}

	private void Start()
	{
	}

	public override void OnInit()
	{
	}

	public void TogglePanel(bool isVisible)
	{
	}

	public void ClosePanel()
	{
	}

	private void ShowPanel()
	{
	}

	private void UpdateGrid()
	{
	}

	public void UpdateUI()
	{
	}

	public void UpdateTown(int townId)
	{
	}

	public void UpdateTown(Transform town)
	{
	}

	private void SetStorageIconColorNEffect(Transform town, UISprite sliderUI, string resPath, float pct)
	{
	}

	private void RemoveListItem()
	{
	}

	public void SortingList()
	{
	}

	private void _SortingList()
	{
	}

	public void HarvestAll()
	{
	}

	[DebuggerHidden]
	public IEnumerator SendHarvestAll()
	{
		return null;
	}

	private void ClickTownListItem(GameObject townObj, int townId)
	{
	}

	public void GoMyCapital()
	{
	}

	public void GoToSelectedTown()
	{
	}

	public void OnTownInfoListBtn()
	{
	}

	public void ShowTownPopularity()
	{
	}

	protected override void OnDestroy()
	{
	}
}
