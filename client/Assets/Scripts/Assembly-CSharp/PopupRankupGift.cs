using System.Collections.Generic;
using LitJson;

public class PopupRankupGift : CaocaoUIModalPopup
{
	private int m_cashshopId;

	private CashShopItemInfo m_cashshopItemInfo;

	private RankUpGift m_rankupGiftData;

	private RankUpGiftReward m_rankupGiftReward;

	private bool m_didPurchased;

	private int totalGold;

	private List<int> m_rankupGiftsIndices;

	private List<RankupGiftGridItem> m_rankupGiftGridItems;

	protected override void Awake()
	{
	}

	public static void Show(int cashshopId, CashShopItemInfo cashshopItemInfo, int rankupGiftId, bool didPurchased, List<int> rankupGiftsIndices)
	{
	}

	public static void Refresh()
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

	private void UpdateUI()
	{
	}

	private bool DidGotAllReward()
	{
		return false;
	}

	private void UpdateGridItems()
	{
	}

	private void UpdateMessage()
	{
	}

	private void UpdateButton()
	{
	}

	public void ShowTerms()
	{
	}

	public void Purchase()
	{
	}

	private void SettlementSuccess()
	{
	}

	public void RequestGetReward(RankupGiftGridItem rankupGiftGridItem)
	{
	}

	private void OnGetRankupGiftReward(JsonData response)
	{
	}
}
