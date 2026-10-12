using System;
using UnityEngine;

public class ChatBlockListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public int Index { get; set; }

		public ulong userId { get; set; }

		public string nationName { get; set; }

		public Action<ulong> onClick { get; set; }
	}

	[SerializeField]
	private UILabelLocalized m_nationName;

	private CellData m_cellData;

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	public void OnBtnClick()
	{
	}
}
