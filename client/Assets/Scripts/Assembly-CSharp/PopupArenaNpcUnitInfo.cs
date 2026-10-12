using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class PopupArenaNpcUnitInfo : CaocaoUIModalPopup
{
	public Transform unitInfoTrans;

	public UILabelLocalized unitDescLabel;

	public UIGrid unitTreasureGrid;

	public UITable passiveGrid;

	public GameObject emptyPassiveGo;

	private UserUnit npcUnit;

	private ArenaNPCSetting pdNpcSetting;

	public static void Show(UserUnit npcUnit, ArenaNPCSetting pdNpcSetting)
	{
	}

	private void InitializeUI()
	{
	}

	private void UpdateUnitInfo()
	{
	}

	private void UpdateUnitDesc()
	{
	}

	private void UpdateUnitTreasures()
	{
	}

	private void UpdatePassives()
	{
	}

	protected override void OnDestroy()
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

	[DebuggerHidden]
	private IEnumerator DelayRepositionGrid()
	{
		return null;
	}

	protected override void ClosePanelImpl()
	{
	}
}
