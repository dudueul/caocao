using System;
using LitJson;
using UnityEngine;

public class PopupChatBlockList : CaocaoUIModalPopup
{
	[SerializeField]
	private UIReuseScrollView m_reuseScrollView;

	[SerializeField]
	private UIReuseGrid m_reuseGrid;

	[SerializeField]
	private UILabelLocalized m_blockCountLabel;

	private ulong m_saveRemoveUserId;

	public Action onBlockRemoveBtn { get; set; }

	public override void ShowPanel()
	{
	}

	private void InitUI()
	{
	}

	private void UpdateUI()
	{
	}

	public void RequestRemoveBlock(ulong removeUserId)
	{
	}

	private void OnChatRemoveBlockRecieved(JsonData response)
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
