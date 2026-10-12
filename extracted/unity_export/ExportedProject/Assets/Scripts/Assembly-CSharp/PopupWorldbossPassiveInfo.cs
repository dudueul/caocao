using System.Collections.Generic;
using UnityEngine;

public class PopupWorldbossPassiveInfo : CaocaoUIModalPopup
{
	private WorldBoss m_pdWorldboss;

	private WorldBossInstance m_worldbossInstance;

	private UITable m_passiveGrid;

	private GameObject m_emptyPassiveGo;

	private Transform m_unitInfo;

	private List<EventScene> m_scriptScenes;

	private List<int> m_activePassiveIds;

	private void InitializeUI()
	{
	}

	private void InitBattleScript()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateUnitInfo(Unit unit)
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

	private void UpdateWorldbossPassive()
	{
	}

	private void AddPassiveListItem(int id, int value)
	{
	}

	public static void Show(WorldBoss pdWorldboss, WorldBossInstance instance)
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
