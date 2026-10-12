using System;
using LitJson;
using UnityEngine;

public class RaidAchievementListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public int Index { get; set; }

		public UserAchievement info { get; set; }

		public Achievement data { get; set; }

		public Action<Achievement, JsonData, bool> OnUpdate { get; set; }
	}

	private CellData m_cellData;

	private bool m_waitRequest;

	private bool m_isTreasure;

	[SerializeField]
	private UILabelLocalized m_labelTitle;

	[SerializeField]
	private UILabelLocalized m_labelDesc;

	[SerializeField]
	private UISprite m_spriteIcon;

	[SerializeField]
	private UILabelLocalized m_labelGauge;

	[SerializeField]
	private UISprite m_spriteGauge;

	[SerializeField]
	private UIButton m_btnComplete;

	[SerializeField]
	private UILabelLocalized m_labelComplete;

	[SerializeField]
	private Transform m_trmRewardGrid;

	[SerializeField]
	private Transform m_trmReceived;

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void UpdateUI()
	{
	}

	public void OnCompleteButtonClicked()
	{
	}

	private void OnCompleteCallback(JsonData response)
	{
	}
}
