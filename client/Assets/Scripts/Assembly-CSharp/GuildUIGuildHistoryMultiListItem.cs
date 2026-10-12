using UnityEngine;

public class GuildUIGuildHistoryMultiListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public int Index { get; set; }

		public GuildHistory history { get; set; }

		public string date { get; set; }

		public CellData(GuildHistory history, string key)
		{
		}
	}

	private CellData m_cellData;

	[SerializeField]
	private GameObject _historyItem;

	[SerializeField]
	private GameObject _historyDayItem;

	public override int Index
	{
		get
		{
			return 0;
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void CheckGameObject(bool day)
	{
	}

	private void UpdateUI()
	{
	}
}
