using System;
using UnityEngine;

public class VisitUserListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public ulong srl;

		public ulong uid;

		public string name;

		public int faceUnitId;

		public int faceUnitSkin;

		public bool deleteMode;

		public bool check;

		public Action<CellData> visitCallback;

		public VisitUserListItem listItem;

		public int Index { get; set; }
	}

	public UnitUI unitUI;

	public UILabelLocalized labelName;

	public GameObject objDeleteMark;

	public UIButton btnVisit;

	public GameObject objCheck;

	public GuildUIMarkName guildUI;

	public bool check
	{
		get
		{
			return false;
		}
		private set
		{
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void UpdateUI()
	{
	}

	public void ToggleDeleteMode(bool deleteMode)
	{
	}

	public void OnBtnCheckDelete()
	{
	}

	public void OnBtnVisit()
	{
	}
}
