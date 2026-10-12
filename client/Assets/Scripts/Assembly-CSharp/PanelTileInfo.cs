using System.Collections.Generic;
using System.Text;
using UnityEngine;

public class PanelTileInfo : CaocaoUIPanel
{
	private static StringBuilder ms_sb;

	private Color COLOR_DARKGRAY;

	private Color COLOR_BLUE;

	private Color COLOR_RED;

	private Color COLOR_ORANGE;

	private Color COLOR_GREEN;

	private Color m_originalColor;

	private Color m_activeColor;

	public GameObject m_tileListObj;

	public GameObject m_tileInfoPopupObj;

	public UISprite m_tileInfoButtonSprite;

	private UIScrollView m_listScrollView;

	private UIScrollView m_popupScrollView;

	private bool m_isVisibleTileListPanel;

	private bool m_isVisibleTileInfoPopup;

	private bool m_isScrollViewDragging;

	private bool m_started;

	public bool isScrollViewDragging
	{
		get
		{
			return false;
		}
	}

	private void Start()
	{
	}

	public override void OnInit()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void Update()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	public void UpdateTileList()
	{
	}

	public void UpdateTilePopUpInfo()
	{
	}

	private void SetSkillTypeIconAvailable(bool isAvailable, UISprite targetUI)
	{
	}

	private void SetUnitStatValue(int value, UILabelLocalized targetUI)
	{
	}

	private List<int> GetJobTypeList()
	{
		return null;
	}

	protected override void ClosePanelImpl()
	{
	}

	public void ToggleTileListPanel()
	{
	}

	public void ToggleTileInfoPopup()
	{
	}

	public void FocusTileOfList(GameObject obj)
	{
	}

	public void FocusTileOfList(int tileID)
	{
	}

	private void UpdateScrollViewDragAmount(int tileId)
	{
	}
}
