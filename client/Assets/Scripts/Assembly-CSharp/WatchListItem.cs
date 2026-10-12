using Multiplay;
using UnityEngine;

public class WatchListItem : MatchListItem
{
	public class WatchCellData : CellData
	{
		public Watch watch { get; set; }

		public override int tierGrade
		{
			get
			{
				return 0;
			}
		}

		public override int point
		{
			get
			{
				return 0;
			}
		}

		public override int opponentTierGrade
		{
			get
			{
				return 0;
			}
		}

		public override int opponentPoint
		{
			get
			{
				return 0;
			}
		}

		public override void SetData(object data)
		{
		}
	}

	[SerializeField]
	private UILabelLocalized battleStep;

	[SerializeField]
	private UILabelLocalized time;

	[SerializeField]
	private UILabelLocalized popularity;

	[SerializeField]
	private UILabelLocalized memberCnt;

	[SerializeField]
	private GameObject noOpponentObject;

	public WatchCellData watchData
	{
		get
		{
			return null;
		}
		private set
		{
		}
	}

	public override void UpdateUI()
	{
	}

	public void UpdateTimeLabel()
	{
	}

	public void ShowWatch()
	{
	}

	public override void OnShare()
	{
	}
}
