using UnityEngine;

public class PanelBattleDetail : PanelSelfRegister2UIComponentsMonobehaviour
{
	public UILabelLocalized m_goodTitle;

	public PrefabInstance m_silverInst;

	public PrefabInstance m_foodInst;

	public PrefabInstance m_honorInst;

	public PrefabInstance m_stampInst;

	public GameObject m_winPanel;

	public GameObject m_losePanel;

	public UILabelLocalized m_honor;

	public UILabelLocalized m_winName;

	public PrefabInstance m_winSilverInst;

	public PrefabInstance m_winFoodInst;

	public UILabelLocalized m_goodDesc;

	public UILabelLocalized m_loseName;

	public UILabelLocalized m_loseNameTitle;

	public UILabelLocalized m_loseMessage;

	public UIGrid m_uiGrid;

	public UIScrollView m_scrollView;

	public UIButton m_watchBattle;

	public UIButton m_reclaim;

	private BattleDetailListItem m_selectedItem;

	public BattleList battleList { get; private set; }

	public BattleDetailListItem selectedItem
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

	public void ShowPanel(BattleList battleList)
	{
	}

	public override void ShowPanel()
	{
	}

	public override void ClosePanel()
	{
	}

	private void InitList()
	{
	}

	private void UpdateUI(BattleData battleData)
	{
	}

	private void ClearList()
	{
	}

	public void ClickWatchBattle()
	{
	}

	public void ClickReclaim()
	{
	}
}
