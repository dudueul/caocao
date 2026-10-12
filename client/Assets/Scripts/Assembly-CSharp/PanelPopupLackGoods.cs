using System;
using System.Collections;
using System.Diagnostics;
using System.Text;
using UnityEngine;

public class PanelPopupLackGoods : CaocaoUIModalPopup
{
	private static StringBuilder ms_sb;

	private UILabelLocalized title;

	private GameObject centerTextOneLine;

	private GameObject centerTextTwoLine;

	private GameObject centerGoods1;

	private GameObject centerGoods2;

	private GameObject btnCancel;

	private GameObject btnHarvest;

	private GameObject btnPurchase;

	private UILabelLocalized labelHarvest;

	private UILabelLocalized labelBuyGold;

	private UILabelLocalized labelBuyGoldValue;

	private bool m_isHarvested;

	private int m_needSilver;

	private int m_needFood;

	private int m_needGold;

	private Action m_callback;

	protected override void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	public static bool CheckSilver(int needSilver, Action callback)
	{
		return false;
	}

	public static bool CheckFood(int needFood, Action callback)
	{
		return false;
	}

	public static bool CheckSilverAndFood(int needSilver, int needFood, Action callback)
	{
		return false;
	}

	public static void Show(int silver, int food, Action callback)
	{
	}

	private void FindChildObjects()
	{
	}

	private void SetParentUIRoot()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	private void OnCancelClick()
	{
	}

	private void OnYesClick()
	{
	}

	private void OnPurchaseClick()
	{
	}

	private void OnProcessCallBack()
	{
	}

	private void OnConfirmClick()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public void Apply(int silver, int food, Action callback)
	{
	}

	private void SetEnableBtn(GameObject targetUI, bool isEnable)
	{
	}

	[DebuggerHidden]
	private IEnumerator HarvestAll(Action<bool> resultCallback = null)
	{
		return null;
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
