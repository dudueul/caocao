using System.Collections.Generic;
using UnityEngine;

public class PanelHeroInfo : SelfRegister2UIComponentsMonobehaviour
{
	private enum TAB_MENU
	{
		None = 0,
		Troop = 1,
		Biographies = 2,
		Spc = 3
	}

	private Color Gradient1TopColor;

	private Color Gradient1BottomColor;

	private Color Gradient2TopColor;

	private Color Gradient2BottomColor;

	private TAB_MENU m_currentTab;

	public UIButton m_btnTroop;

	public UIButton m_btnBiographies;

	public UIButton m_btnSpecial;

	public UIButton m_btnHeroList;

	public UIPanel m_panelTroop;

	public UIPanel m_panelBiographies;

	public UIPanel m_panelSpecial;

	private ulong m_unitSrl;

	private GameObject m_prevFocusUnitObj;

	private const float PosBtnY = 267f;

	private const float MOVE_Y = 5f;

	private const string btnNormalSprite = "btn_all_tab_n_n";

	private const string btnPressedSprite = "btn_all_tab_s_n";

	private TAB_MENU currentTab
	{
		get
		{
			return TAB_MENU.None;
		}
		set
		{
		}
	}

	public ulong focusedUnitSrl
	{
		get
		{
			return 0uL;
		}
	}

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void UpdateUI()
	{
	}

	public void ChangeUnit(ulong unitSrl)
	{
	}

	public void ChangeUnit(UnitNameListItem current)
	{
	}

	private UIButton GetTabButton(TAB_MENU tab)
	{
		return null;
	}

	private UIPanel GetCurrentTabPanel(TAB_MENU tab)
	{
		return null;
	}

	private void UpdateTabBtn(TAB_MENU tab)
	{
	}

	private void UpdateTabUI()
	{
	}

	private void ToggleColorTabBtnName(UIButton targetBtn, bool isActive)
	{
	}

	private List<ulong> GetUnitList()
	{
		return null;
	}

	private void UpdateUnitList()
	{
	}

	private void RemoveAllUnit()
	{
	}

	private void SetFocusUnitList(GameObject unitObj, bool isFocused)
	{
	}
}
