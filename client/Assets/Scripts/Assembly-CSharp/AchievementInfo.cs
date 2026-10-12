using System;
using LitJson;

public class AchievementInfo : UIReuseScrollViewCell
{
	public class AchievementInfoCellData : IReuseCellData
	{
		public int Index { get; set; }

		public UserAchievement achievement { get; set; }

		public Achievement info { get; set; }

		public Action<Achievement, JsonData, bool> OnAchievementUpdate { get; set; }
	}

	private AchievementInfoCellData m_achievementInfoCellData;

	private bool waitRequest;

	private bool isTreasure;

	private const int MAX_ITEM_COUNT = 3;

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	public void OnComplete()
	{
	}

	private void UpdateUI()
	{
	}

	private void SetIcon()
	{
	}

	private void SetGauge()
	{
	}

	private void SetButton(bool recieved)
	{
	}

	private void SetItemInfo()
	{
	}

	private void SetBlankSlot(int itemCount)
	{
	}

	private void OnCompleteCallback(JsonData response)
	{
	}
}
