using UnityEngine;

public class PopupCompetitionBattleInfo : CaocaoUIModalPopup
{
	[SerializeField]
	private GameObject m_battleInfoGo;

	[SerializeField]
	private UIScrollView m_battleInfoScrollView;

	[SerializeField]
	private UITable m_battleInfoTable;

	[SerializeField]
	private GameObject m_ruleGo;

	[SerializeField]
	private UIScrollView m_ruleScrollView;

	[SerializeField]
	private UITable m_ruleTable;

	[SerializeField]
	private CaocaoTabButtons m_tabButtons;

	private GameModeRule m_gameModeRule;

	private bool m_initedBattleInfoUi;

	private bool m_initedRuleUi;

	private EnumData.BattleMode m_battleMode;

	public static void Show(EnumData.BattleMode battleMode)
	{
	}

	private void InitUi()
	{
	}

	public void OnTabChanged(CaocaoButton button)
	{
	}

	private void UpdateBattleInfo()
	{
	}

	private void UpdateRule()
	{
	}

	private void ClearBattleInfoTable()
	{
	}

	private void ClearRuleTable()
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

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}
