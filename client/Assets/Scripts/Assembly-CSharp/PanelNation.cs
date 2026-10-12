using System;
using UnityEngine;

public class PanelNation : PanelSelfRegister2UIComponentsMonobehaviour
{
	public enum NationTab
	{
		Mercenary = 0,
		Research = 1,
		SecretScheme = 2,
		Nation = 3
	}

	private readonly Color conquerredNationColor;

	private readonly Color otherNationColor;

	private NationTab currentTab;

	[SerializeField]
	private CaocaoTabButtons tabButtons;

	private GameObject m_mercenaryPanel;

	public GameObject m_researchPanel;

	public GameObject m_secretSchemePanel;

	public GameObject m_nationPanel;

	public UILabelLocalized m_allUnitCount;

	public UILabelLocalized m_availableUnitCount;

	public UILabelLocalized m_injuredUnitCount;

	public UILabelLocalized m_townCount;

	public UILabelLocalized m_multiBattleWin;

	public UILabelLocalized m_multiBattleLose;

	public UILabelLocalized m_shamBattleWin;

	public UILabelLocalized m_shamBattleLose;

	public UILabelLocalized m_eventBattleWin;

	public UILabelLocalized m_eventBattleLose;

	public UILabelLocalized m_nationNameLabel;

	private Transform m_worldMapBg;

	private UIScrollView m_stateScrollView;

	private UIGrid m_stateGrid;

	private GameObject m_noConquered;

	private bool m_started;

	public static void Show()
	{
	}

	public static void ShowDetail(NationTab tab)
	{
	}

	protected override void Awake()
	{
	}

	private void InitUI()
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}

	private void Start()
	{
	}

	public override void OnInit()
	{
	}

	public override void ShowPanel()
	{
	}

	public override void ClosePanel()
	{
	}

	private void LoadMercenaryPanelPrefab()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateMercenaryPanel()
	{
	}

	private void UpdateResearchTab()
	{
	}

	private void UpdateResearchInfo()
	{
	}

	private void UpdateSecretSchemePanel()
	{
	}

	private void UpdateNationPanel()
	{
	}

	private void RequestShamInfos()
	{
	}

	private void UpdateShamInfos()
	{
	}

	private void UpdateTabActive(CaocaoRadioButton tabBtn, bool focus)
	{
	}

	public void ClickChangeNationName()
	{
	}

	public static void ShowNationNamePopup(int cost, bool isTutorial, Action closeCallback = null)
	{
	}

	public void SetNationName(string nationName)
	{
	}

	private void UpdateStateInfo()
	{
	}

	private void OnClickStateReward(State stateData)
	{
	}
}
