using System.Collections.Generic;
using BattlePlay;
using UnityEngine;

public class PopupBattleUnitInfoList : CaocaoUIModalPopup
{
	[SerializeField]
	private UIReuseScrollView m_reuseScrollView;

	[SerializeField]
	private UIReuseGrid m_reuseGrid;

	private int curTab;

	private List<BattleUser> m_lstUser;

	private bool prevPauseState;

	private GameObject[] m_tabButton;

	private GameObject[] m_select;

	private GameObject[] tabButton
	{
		get
		{
			return null;
		}
	}

	private GameObject[] select
	{
		get
		{
			return null;
		}
	}

	protected override void Awake()
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

	public static void Show(EnumData.BattleMode battleMode)
	{
	}

	private static void CompetitionShow()
	{
	}

	private static void BattleFieldShow()
	{
	}

	private static void PvpShow()
	{
	}

	private static void ArenaShow()
	{
	}

	public void OnTabButton()
	{
	}

	private void InitiateUI()
	{
	}

	private void ProfileSetting(Transform profile, BattleUser user)
	{
	}

	private void UpdateUI()
	{
	}

	private void UnitListSetting(BattleUser user)
	{
	}

	private void BattleFieldMyUnitListSetting()
	{
	}

	private void BattleFieldOpponentUnitListSetting()
	{
	}

	private void AnnihilationWarMyUnitListSetting()
	{
	}

	private void AnnihilationWarOpponentUnitListSetting()
	{
	}

	private void UnitListSettingAnnihilation(BattleUser user)
	{
	}

	private void UnitListSettingBrawl(BattleUser user)
	{
	}

	private int BrawlUnitSetting(int unitCount, ulong uid, BattleUnit battleUnit)
	{
		return 0;
	}

	private void ArenaOwnUnitListSetting()
	{
	}

	private void ArenaEnemyUnitListSetting()
	{
	}

	private void OnTurnChanged()
	{
	}

	private void Dispose()
	{
	}

	protected override void OnDestroy()
	{
	}
}
