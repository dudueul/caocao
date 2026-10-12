using LitJson;
using UnityEngine;

public class TimeBoxSlotItem : MonoBehaviour
{
	public Transform emptyBox;

	public Transform box;

	public UIButton openBoxBtn;

	public UIButton payBoxBtn;

	public UIButton dropBoxBtn;

	public UILabelLocalized boxName;

	public UITexture boxIcon;

	public UILabelLocalized remainTime;

	public Transform bottomInfo;

	public UILabelLocalized immediateOpenCost;

	public Transform tooltipTranf;

	public UILabelLocalized tooltipText;

	public Transform slot;

	public UIButton purchaseSlotBtn;

	public UILabelLocalized purchaseSlotLabel;

	private UserTimeBox m_userTimeBox;

	private TimeBox boxPd;

	private int purchaseCost;

	private int m_index;

	public int index
	{
		get
		{
			return 0;
		}
	}

	public void UpdateUI(UserTimeBox userTimeBox)
	{
	}

	public void UpdateUI(int index)
	{
	}

	private void UpdateBoxUI()
	{
	}

	private void UpdateFuncByTimer()
	{
	}

	private void UpdateRemainTime()
	{
	}

	private void UpdateBoxOpenCost()
	{
	}

	private void UpdateBoxName()
	{
	}

	private void UpdateBoxIcon()
	{
	}

	private void UpdateOpenButton()
	{
	}

	private void UpdateDropButton()
	{
	}

	private void UpdatePurchaseSlotUI()
	{
	}

	private void SetTooltipInfo()
	{
	}

	public void PurchaseSlot()
	{
	}

	public void OnPurchaseSlot(JsonData response)
	{
	}

	public void OpenBox()
	{
	}

	public void OnOpenBox(JsonData response)
	{
	}

	private void OnDropBox(JsonData response)
	{
	}

	public void OpenBoxImmediately()
	{
	}

	public void OnOpenBoxImmediately(JsonData response)
	{
	}

	public void DropBox()
	{
	}

	private string GetErrorString(string errorCode)
	{
		return null;
	}

	private int GetAddSlotGoldCost(int addSlotIndex)
	{
		return 0;
	}
}
