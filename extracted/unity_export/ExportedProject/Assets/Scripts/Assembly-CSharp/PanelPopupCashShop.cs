using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class PanelPopupCashShop : PanelPopupBaseShop
{
	private List<RecommendShopItemInfo> m_recommendShopInfos;

	private List<CashShopItemInfo> m_cashShopGoodsInfos;

	private Dictionary<int, EventShopInfo> m_dicEventShopInfos;

	private bool m_limitShopOpen;

	private Transform m_tabBtnSpecial;

	private Transform m_tabBtnLimit;

	private Transform m_tabBtnPackage;

	private Transform m_tabBtnRecommend;

	private List<GoodsShopItemInfo> m_goodsShopItemList;

	private bool m_specialShopOpen;

	private int m_requestQueueCount;

	private Transform tabBtnSpecial
	{
		get
		{
			return null;
		}
	}

	private Transform tabBtnLimit
	{
		get
		{
			return null;
		}
	}

	private Transform tabBtnPackage
	{
		get
		{
			return null;
		}
	}

	private Transform tabBtnRecommend
	{
		get
		{
			return null;
		}
	}

	private void UpdateTab()
	{
	}

	private void UpdateUI()
	{
	}

	public void UpdateEventTimer()
	{
	}

	private void DisableEventBanner()
	{
	}

	private void UpdateRemainingTime()
	{
	}

	private new void UpdateVipButton()
	{
	}

	private void UpdateRecommend()
	{
	}

	private void UpdateGoldGrid()
	{
	}

	private void UpdateSeasonTicket()
	{
	}

	private void UpdatePackageGrid()
	{
	}

	private void UpdateGoods(int tabType)
	{
	}

	private void UpdateLimit()
	{
	}

	private void RequestShopList()
	{
	}

	private void OnRequestShopList(JsonData response)
	{
	}

	private void RequestGoodsShopList()
	{
	}

	private void OnRequestGoodsShopList(JsonData response)
	{
	}

	private void RequestForInitialize()
	{
	}

	private void OnRequestRecommendShopList(JsonData response)
	{
	}

	private void OnRequestRecommendShopListtForInitialize(JsonData response)
	{
	}

	private void OnRequestShopListForInitialize(JsonData response)
	{
	}

	private void OnRequestGoodsShopListForInitialize(JsonData response)
	{
	}

	private void OnRequestForInitialize()
	{
	}

	private void OnRepositionTabButtons()
	{
	}

	public new void OnPanelClose()
	{
	}

	private new void OnCloseBtnClicked()
	{
	}

	private void OnRecommendTabClicked()
	{
	}

	private void OnSpecialTabClicked()
	{
	}

	private void OnGoldTabClicked()
	{
	}

	private void OnSeasonTicketClicked()
	{
	}

	private void OnPackageBtnClicked()
	{
	}

	private void OnGoodsBtnClicked()
	{
	}

	private void OnLimitBtnClicked()
	{
	}

	private new void OnVipBtnClicked()
	{
	}

	private new void OnHistoryBtnClicked()
	{
	}

	private new void OnSpcCommActBtnClicked()
	{
	}

	private new void OnSettlementActBtnClicked()
	{
	}

	private new void OnTreasureProbabilityBtnClicked()
	{
	}

	private new void OnChargeItemChanceInfoBtnClicked()
	{
	}

	protected override void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void OnDisable()
	{
	}

	public void OnTabClicked(CaocaoButton button)
	{
	}

	private void Init()
	{
	}

	[DebuggerHidden]
	private IEnumerator initWait()
	{
		return null;
	}

	public static void Show()
	{
	}

	public static void Show(TabMenu tab)
	{
	}

	public static void Refresh()
	{
	}

	public static void RefreshGoodShopGrid()
	{
	}

	public static void MoveToGoldTab()
	{
	}

	private string GetTabName()
	{
		return null;
	}
}
