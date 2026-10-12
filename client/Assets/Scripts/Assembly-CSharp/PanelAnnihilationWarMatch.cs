using System.Collections;
using System.Diagnostics;

public class PanelAnnihilationWarMatch : PanelSelfRegister2UIComponentsMonobehaviour
{
	public UIAnnihilationWarMatchInfo[] annihilationWarMatchInfos;

	public UISprite seasonTierIcon;

	public UILabelLocalized seasonRecordLabel;

	public UILabelLocalized streakWinCntLabel;

	public UILabelLocalized refreshCostLabel;

	public UIBattleFormation battleFormation;

	public const string popupName = "PanelAnnihilationWarMatch";

	private UIAnnihilationWarMatchInfo m_selectedMatchInfo;

	private bool m_showingExitPopup;

	protected override void Awake()
	{
	}

	public static void Show(bool init = false)
	{
	}

	private void Init()
	{
	}

	private void OnEnable()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void Update()
	{
	}

	public void OnClickRefreshMatchInfo()
	{
	}

	private void RefreshMatchInfo()
	{
	}

	private void UpdateMatchInfos()
	{
	}

	private void OnSelectMatchInfo(UIAnnihilationWarMatchInfo matchInfo)
	{
	}

	public void GotoLobby()
	{
	}

	public void GotoEditDeck()
	{
	}

	public void GotoBattle()
	{
	}

	private void BattleStart()
	{
	}

	private bool CheckDeckCost(UserUnitGroup userUnitGroup)
	{
		return false;
	}

	private bool CheckChangingTreasures(UserUnitGroup userUnitGroup)
	{
		return false;
	}

	[DebuggerHidden]
	private IEnumerator GotoBattleMap()
	{
		return null;
	}

	private void ApplyOpponentInfo()
	{
	}

	private void UpdateUI()
	{
	}

	public override void ShowPanel()
	{
	}

	public override void ClosePanel()
	{
	}
}
