using System;
using UnityEngine;

public class GuildUICompetitionRankingListItem : UIReuseScrollViewCell
{
	[SerializeField]
	private UILabelLocalized nationName;

	[SerializeField]
	private UILabelLocalized tierName;

	[SerializeField]
	private UILabelLocalized ranking;

	[SerializeField]
	private UILabelLocalized point;

	[SerializeField]
	private UILabelLocalized totalPoint;

	public static Action<int> onClick;

	protected GuildUIMemberListObject.GuildMemberCellData m_cellData;

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

	private void UpdateUI()
	{
	}

	private void OnClick()
	{
	}
}
