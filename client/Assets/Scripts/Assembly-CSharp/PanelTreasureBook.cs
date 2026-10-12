using System.Collections.Generic;
using UnityEngine;

public class PanelTreasureBook : CaocaoUIModalPopup
{
	private enum TAB_MAIN_MENU
	{
		Scenario = 0,
		Multi = 1,
		Private = 2,
		HeroOwned = 3,
		NONE = 4
	}

	private const string ITEM = "Item_";

	private int m_selectedItemId;

	public CaocaoTabButtons mainTab;

	public CaocaoTabButtons subTab;

	private TAB_MAIN_MENU m_currentMainTab;

	private EnumData.TreasureTapMenu m_currentSubTab;

	private float m_multiItemCurrentCnt;

	private float m_scenarioItemCurrentCnt;

	private float m_privateItemCurrentCnt;

	private float m_heroOwnedItemCurrentCnt;

	private float m_multiItemTotalCnt;

	private float m_scenarioItemTotalCnt;

	private float m_privateItemTotalCnt;

	private float m_heroOwnedItemTotalCnt;

	private bool m_showOnlyMyTreasure;

	private GameObject m_showOnlyMyTreasureGo;

	public GameObject m_closeBtn;

	private Transform m_itemInfo;

	private bool m_started;

	private GameObject showOnlyMyTreasureGo
	{
		get
		{
			return null;
		}
	}

	public Transform itemInfo
	{
		get
		{
			return null;
		}
	}

	protected override void Awake()
	{
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

	public static void ShowAsPopup()
	{
	}

	public static void ShowAsPanel()
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

	protected override void ClosePanelImpl()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	private void RemoveAllItem()
	{
	}

	private void UpdateGrid(TAB_MAIN_MENU mainIdx, EnumData.TreasureTapMenu subIdx, bool resetScroll = true)
	{
	}

	public void UpdateDetailUI()
	{
	}

	public void OnMainTabChanged(CaocaoButton button)
	{
	}

	public void OnSubTabChanged(CaocaoButton button)
	{
	}

	private List<Item> GetTreasureItems(TAB_MAIN_MENU mainIdx, EnumData.TreasureTapMenu subIdx)
	{
		return null;
	}

	private void GetItemCount()
	{
	}

	private void OnItemSelected(TreasureBookCellData selectedCellData)
	{
	}

	public void ToggleMyTreasureShow()
	{
	}
}
