using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PopupLinkedGoods : CaocaoUIModalPopup
{
	public Transform titleLabel;

	public Transform timeLabel;

	public Transform descLabel;

	public UIButton m_resetBtn;

	public UILabelLocalized m_resetLabel;

	public Transform m_notice;

	public Transform m_noticeJP;

	public Transform m_lowerTransf;

	public UIGrid m_grid;

	private UserLinkedGoods userLinkedGoods;

	private LinkedGoods linkedGoodsPd;

	public List<LinkedGoodsNode> goodsNodes;

	private List<CashShopItemInfo> m_cashShopGoodsInfos;

	private List<GoodsShopItemInfo> m_goodsShopItemInfos;

	public static void Show(LinkedGoods linkedGoodsPd)
	{
	}

	protected override void Awake()
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

	private void Init()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateUpperUI()
	{
	}

	private void UpdateResetButton()
	{
	}

	private void UpdateTime()
	{
	}

	private void UpdateLowerUI()
	{
	}

	private void RequestShopInfo()
	{
	}

	private void UpdateGrid(JsonData response)
	{
	}

	private void ClearGrid()
	{
	}

	public void OnClickReset()
	{
	}

	private void RequestReset()
	{
	}

	private void OnReset(JsonData response)
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
