using UnityEngine;

public class UIGuildTopRanker : MonoBehaviour
{
	[SerializeField]
	private GuildUIMarkName guildInfoComp;

	[SerializeField]
	private UILabel nationName;

	[SerializeField]
	private UILabelLocalized rankerNum;

	[SerializeField]
	private GameObject prevRankObj;

	[SerializeField]
	private UILabelLocalized prevRanking;

	[SerializeField]
	private UISprite prevRankIcon;

	[SerializeField]
	private UIGrid topRankerGrid;

	[SerializeField]
	private UILabelLocalized[] topRankers;

	[SerializeField]
	private GameObject viewListBtn;

	[SerializeField]
	private ClickHandler btnClickHandler;

	private RankListItem.GuildRankerCellData rankerCellData;

	public void UpdateUI(IReuseCellData cell)
	{
	}

	private void Empty()
	{
	}

	public void OnListBtnClick()
	{
	}
}
