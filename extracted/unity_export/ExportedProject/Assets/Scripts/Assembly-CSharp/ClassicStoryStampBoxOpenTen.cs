using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Runtime.InteropServices;
using UnityEngine;

public class ClassicStoryStampBoxOpenTen : CaocaoUIModalPopup
{
	[StructLayout((LayoutKind)0, Size = 8)]
	private struct ItemInfo
	{
		public int id;

		public int amount;

		public ItemInfo(int _id, int _amount)
		{
			id = 0;
			amount = 0;
		}
	}

	private const int MAX_COUNT_GACHA = 11;

	protected override void Awake()
	{
	}

	public void ShowPopup(List<TreasurePurchasePopup.TreasureBoxResult> results, string boxEffectName)
	{
	}

	private void SetBoxFx(string boxEffect)
	{
	}

	private void SetTreasureBoxResults(List<TreasurePurchasePopup.TreasureBoxResult> results)
	{
	}

	private void SetRune(int index, ulong runeSrl, int rareTier, bool fixedTier)
	{
	}

	private void SetRune(Transform tr, UserRuneItem runeItem, bool fixedTier)
	{
	}

	private void SetItems(List<ItemInfo> itemList)
	{
	}

	private void SetItem(int index, int itemId, int itemCount, int rareTier, int cardTier = 0)
	{
	}

	private void SetItem(Transform tr, Item item, int amount, int cardTier = 0)
	{
	}

	private int GetRareTier(EnumData.TreasureBoxItemType itemType)
	{
		return 0;
	}

	private void SetMaterial(Transform tr, string tierlName)
	{
	}

	private void SetButtons()
	{
	}

	private void SetButtonAction(string path, EventDelegate.Callback callback)
	{
	}

	private void SetButtonActive(string path, bool active)
	{
	}

	private string GetItemTierlName(int tier)
	{
		return null;
	}

	private string GetRuneItemTierlName(int tier, bool fixedTier)
	{
		return null;
	}

	private void OnOpenAll()
	{
	}

	private void OnOepnOne()
	{
	}

	public void OnShowItemTooltip(ItemUI itemUI)
	{
	}

	public void OnShowRuneItemTooltip(RuneUI runeUI)
	{
	}

	[DebuggerHidden]
	private IEnumerator OpenAll()
	{
		return null;
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
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
}
