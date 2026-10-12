using Multiplay;
using UnityEngine;

public class ReplayListItem : MatchListItem
{
	public class ReplayCellData : CellData
	{
		public Replay replay { get; set; }

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
	private UILabelLocalized time;

	[SerializeField]
	private UILabelLocalized openRange;

	[SerializeField]
	private UILabelLocalized recommendedCount;

	[SerializeField]
	private UITexture battleMap;

	[SerializeField]
	private Transform openRangeBtn;

	public ReplayCellData replayData
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

	public void ShowReplay()
	{
	}

	public override void OnShare()
	{
	}

	public void SelectOpenRange()
	{
	}
}
