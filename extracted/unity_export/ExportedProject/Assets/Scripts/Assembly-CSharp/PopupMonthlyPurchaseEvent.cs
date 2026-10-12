using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PopupMonthlyPurchaseEvent : CaocaoUIModalPopup
{
	public UILabelLocalized m_title;

	public UILabelLocalized m_subTitle;

	public UILabelLocalized m_period;

	public PurchaseEventBonusItem m_bonusItemTreasure;

	public PurchaseEventBonusItem m_bonusEmoticon;

	public PurchaseEventBonusItem m_bonusSkin;

	public UISlider m_gauge;

	public UIGrid m_cahsshopGrid;

	public Transform m_notice;

	public Transform m_noticeJP;

	private int eventId;

	private PurchaseEvent eventPd;

	private DateTime startTime;

	private DateTime endTime;

	private UserCumulativePurchaseAmount userCumulativePurchaseAmount;

	private Transform m_noticeBG;

	private List<CashShopItemInfo> m_cashShopGoodsInfos;

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}

	public static void Show(int currentEventId)
	{
	}

	public void OnDisable()
	{
	}

	public void OnEnable()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	private void UpdateUpperUIForResponseHandler(JsonData data)
	{
	}

	private void UpdateLowerUIForResponseHandler(JsonData data)
	{
	}

	private void Initialize()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateUpperUI()
	{
	}

	private void UpdateLowerUI()
	{
	}

	private void UpdateNoticeUI()
	{
	}

	private void UpdateUpperText()
	{
	}

	private void UpdateBonusItemUI()
	{
	}

	private void RequestShopInfo()
	{
	}

	private void UpdateCashshopGrid(JsonData response)
	{
	}

	private void ClearGrid()
	{
	}

	public void OnClickNoticeDetail()
	{
	}

	public void OnClickSpcCommTransAct()
	{
	}

	public void OnClickSettlementAct()
	{
	}

	public void OnTreasureProbabilityBtnClicked()
	{
	}
}
