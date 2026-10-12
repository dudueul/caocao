using System;
using UnityEngine;

public class ChatListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public bool viewEmoticon;

		public int Index { get; set; }

		public int width { get; set; }

		public int height { get; set; }

		public EventArgs msg { get; set; }

		public Action<string, string, int> onClick { get; set; }
	}

	[SerializeField]
	private UIWidget m_msgBg;

	[SerializeField]
	private UILabelLocalized m_msg;

	[SerializeField]
	private UILabelLocalized m_systemMsg;

	[SerializeField]
	private UILabelLocalized m_share;

	[SerializeField]
	private UILabelLocalized m_labelShare;

	[SerializeField]
	private ClickHandler m_clickHandler;

	[SerializeField]
	private GameObject m_objEmoticonMsg;

	[SerializeField]
	private UILabelLocalized m_emoticonNameLabel;

	[SerializeField]
	private UIWidget m_emoticonBg;

	[SerializeField]
	private UITexture[] m_emoticonList;

	private CellData m_cellData;

	private int m_msgIndex;

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void SetMsg()
	{
	}

	private void SetSize()
	{
	}

	private void OnClick()
	{
	}

	public void OnBtnPlaySharedBattle()
	{
	}

	private void OnUserRegisteredCallback()
	{
	}

	private void OnDestroy()
	{
	}
}
