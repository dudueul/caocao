using System;
using Chat;
using UnityEngine;

public class ChatChannelListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public int Index { get; set; }

		public Room roomData { get; set; }

		public Action<string> onClick { get; set; }
	}

	[SerializeField]
	private UILabelLocalized m_title;

	[SerializeField]
	private UILabelLocalized m_status;

	private CellData m_cellData;

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	public void OnClick()
	{
	}

	private string GetStringRoomStatus(ERoomStatus status)
	{
		return null;
	}
}
