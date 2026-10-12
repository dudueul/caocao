using UnityEngine;

public class ClassicStoryStampBoxOpenOne : CaocaoUIModalPopup
{
	protected override void Awake()
	{
	}

	public void ShowPopup(TreasurePurchasePopup.TreasureBoxResult result, string boxEffectName)
	{
	}

	private void SetTreasureBoxRune(Transform item1, ulong itemSrl, bool fixedTier)
	{
	}

	private void SetTreasureBoxItem(Transform item1, int itemId, int itemCount, int cardTier)
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
