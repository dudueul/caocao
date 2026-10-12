using System.Collections.Generic;
using UnityEngine;

public class UIGuildWarPowerManagement : CaocaoUIModalPopup
{
	[SerializeField]
	private UIGrid tr_barracksGrid;

	private List<UserQuickBattleDeck> quickBattleDecks;

	private static UIGuildWarPowerManagement _self;

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

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void UpdateTimer()
	{
	}

	protected override void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	public static void Show()
	{
	}

	private void AddQuickBattleDeck()
	{
	}

	private void UpdateUI(bool refresh = true)
	{
	}

	private void OnPurchaseCallback(bool succeed)
	{
	}

	private void UpdateBarracksList(bool refresh)
	{
	}

	private void UpdateBarracksListData()
	{
	}

	public int[] GetBarracksUnitList(ref List<UserQuickBattleDeckUnit> deckUnits)
	{
		return null;
	}

	public void OnClickBtnAllUnitClear()
	{
	}

	public bool IsAlreadySelected(int unitId)
	{
		return false;
	}

	public void OnClickBtnAuto()
	{
	}

	private void OnAutoBarracks()
	{
	}

	public void Closed()
	{
	}

	public void OnClickBtnClose()
	{
	}

	public void OnClickBtnSave()
	{
	}

	private void OnSaveBarracks()
	{
	}
}
