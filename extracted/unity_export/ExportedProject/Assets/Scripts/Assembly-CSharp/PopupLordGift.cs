using System.Collections.Generic;
using LitJson;

public class PopupLordGift : CaocaoUIModalPopup
{
	private int m_cashshopId;

	private CashShopItemInfo m_cashshopItemInfo;

	private RankUpGift m_rankupGiftData;

	private RankUpGiftReward m_rankupGiftReward;

	private bool m_didPurchased;

	private List<int> m_rankupGiftsIndices;

	private List<LordGiftGridItem> m_rankupGiftGridItems;

	private LordGiftButton m_lordGiftButton;

	private bool m_isRewardTicketItem;

	protected override void Awake()
	{
	}

	public static void Show(int cashshopId, CashShopItemInfo cashshopItemInfo, int rankupGiftId, bool didPurchased, List<int> rankupGiftsIndices, LordGiftButton lordGiftButton)
	{
	}

	public void Refresh()
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

	public void RequestGetReward(LordGiftGridItem rankupGiftGridItem)
	{
	}

	private void OnGetRankupGiftReward(JsonData response)
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
