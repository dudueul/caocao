using UnityEngine;

public class TreasureBoxOpenDirection : CaocaoUIModalPopup
{
	protected override void Awake()
	{
	}

	public void StartOpenRuneDirection1(ulong srl, bool fixedTier, string boxEffect)
	{
	}

	public void StartOpenDirection1(TreasureBoxItemInfo boxInfo, TreasurePurchasePopup.TreasureBoxResult result, int bonusId)
	{
	}

	private void SetTreasureBoxRune(Transform item1, ulong itemSrl, bool fixedTier, int bonusId)
	{
	}

	private void SetTreasureBoxItem(Transform item1, Transform item1Change, TreasurePurchasePopup.TreasureBoxResult result, BoxShop boxPd, int bonusId)
	{
	}

	private void ShowBonus(int bonusId, PlayMakerFSM fsm)
	{
	}

	public void StartOpenDirection10()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public static void ShowItemTooltip(ItemUI itemUI)
	{
	}

	public static void ShowRuneItemTooltip(RuneUI runeUI)
	{
	}

	public override void OnTappingOutSide()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	private string GetItemTierlName(int tier)
	{
		return null;
	}

	private string GetRuneItemTierlName(int tier, bool fixedTier = false)
	{
		return null;
	}
}
