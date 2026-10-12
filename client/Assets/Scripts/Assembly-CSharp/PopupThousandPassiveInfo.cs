using Caocao;
using UnityEngine;

public class PopupThousandPassiveInfo : CaocaoUIModalPopup
{
	public Transform unitInfoTrans;

	public UITable passiveGrid;

	public GameObject emptyPassiveGo;

	private int m_baseLevel;

	private TroopsGrade m_enemyUnitType;

	private ThousandBattleInfo.UnitInfoBase m_unitInfo;

	public static void Show(ThousandBattleInfo.UnitInfoBase unitInfo, int baseLevel, TroopsGrade enemyUnitType)
	{
	}

	private void InitializeUI()
	{
	}

	private void UpdatePassives()
	{
	}

	private void UpdateUnitGridItems()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
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
}
