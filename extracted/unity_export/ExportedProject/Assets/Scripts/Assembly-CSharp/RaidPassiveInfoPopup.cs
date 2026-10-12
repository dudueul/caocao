using System.Collections.Generic;
using UnityEngine;

public class RaidPassiveInfoPopup : CaocaoUIModalPopup
{
	public Raid m_pdRaid;

	public UITable passiveGrid;

	private Transform m_unitInfo;

	private List<EventScene> m_scriptScenes;

	private List<int> m_activePassiveIds;

	private GameObject m_listItemPrefab;

	private void InitializeUI()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateUnitPassive(Unit pdUnit)
	{
	}

	private void UpdateTreasurePassive()
	{
	}

	private void UpdateJobPassive(Unit pdUnit)
	{
	}

	private void UpdateScriptInfo()
	{
	}

	public static void Show(Raid pdRaid)
	{
	}

	private void InitBattleScript()
	{
	}

	private void AddPassiveListItem(int id, int value)
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
