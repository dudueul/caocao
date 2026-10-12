using System;
using LitJson;
using UnityEngine;

public class UserArenaInfoListItemUI : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public Action<CellData> keepBtnCallback;

		public int Index { get; set; }

		public ulong srl { get; set; }

		public ulong uid { get; set; }

		public DateTime battleDate { get; set; }

		public int clearLevel { get; set; }

		public int stamp { get; set; }

		public JsonData unitGroup { get; set; }

		public bool keep { get; set; }

		public CellData(UserArenaRecord record, Action<CellData> keepBtnCallback)
		{
		}

		public CellData(JsonData jsonData, Action<CellData> keepBtnCallback)
		{
		}
	}

	[SerializeField]
	private UILabelLocalized labelTime;

	[SerializeField]
	private GameObject objComplete;

	[SerializeField]
	private GameObject objPerfect;

	[SerializeField]
	private UILabelLocalized labelWin;

	[SerializeField]
	private UILabelLocalized labelStamp;

	[SerializeField]
	private GameObject objKeepButton;

	[SerializeField]
	private GameObject objKeepSprite;

	[SerializeField]
	private UIGrid gridFace;

	private CellData data
	{
		get
		{
			return null;
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateFaceUI(Transform obj, JsonData unitData)
	{
	}

	public void OnKeepButton()
	{
	}
}
