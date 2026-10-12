using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Runtime.InteropServices;
using UnityEngine;

public class TreasureBoxOpenTenGacha : CaocaoUIModalPopup
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

	protected const int MAX_COUNT_GACHA = 11;

	private BoxShop boxShopInfo;

	private string payType;

	private int costIdx;

	protected override void Awake()
	{
	}

	public void ShowPopup(BoxShop boxShop, List<TreasurePurchasePopup.TreasureBoxResult> items, string costType, int costIdx, int bonusId = 0, bool canBeRetry = false)
	{
	}

	public void ShowRunePopup(List<ulong> gaineds, int fixedTierIndex, RuneBoxShop runeBoxShop)
	{
	}

	private void SetBonus(int bonusId, int bonusAmount)
	{
	}

	protected void SetBoxFx(string boxEffect)
	{
	}

	private void SetTreasureBoxResults(List<TreasurePurchasePopup.TreasureBoxResult> results)
	{
	}

	private void SetRunes(List<ulong> runeSrls, int fixedTierIndex)
	{
	}

	private void SetRune(int index, ulong runeSrl, int rareTier, bool fixedTier)
	{
	}

	private void SetRune(Transform tr, UserRuneItem runeItem, bool fixedTier)
	{
	}

	protected void SetItem(int index, int itemId, int count, int rareTier, EventDelegate.Callback onClickCallback = null)
	{
	}

	protected void SetItem(int index, TreasurePurchasePopup.TreasureBoxResult result, int rareTier, EventDelegate.Callback onClickCallback = null)
	{
	}

	protected void _setItemFrame(Transform tr, int originId, int originCount, int changeId, int changeCount, int cardTier, EventDelegate.Callback onClickCallback = null)
	{
	}

	protected int GetRareTier(EnumData.TreasureBoxItemType itemType)
	{
		return 0;
	}

	private void SetMaterial(Transform tr, string tierlName)
	{
	}

	private void SetButtons(RuneBoxShop runeBoxShop)
	{
	}

	private void SetButtons(BoxShop boxShop, bool canBeRetry)
	{
	}

	protected void SetButtonAction(string path, EventDelegate.Callback callback)
	{
	}

	protected void SetButtonActive(string path, bool active)
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

	private void OnRetry()
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

	private void BroadCastTenGacha(BoxShop boxPd, List<TreasurePurchasePopup.TreasureBoxResult> results)
	{
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
