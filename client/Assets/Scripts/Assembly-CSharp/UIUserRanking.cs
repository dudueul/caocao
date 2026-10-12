using UnityEngine;

public class UIUserRanking : MonoBehaviour
{
	[SerializeField]
	private GuildUIMarkName guildInfoComp;

	[SerializeField]
	private UserCommonProfileSmall userProfile;

	[SerializeField]
	private UILabel nationName;

	[SerializeField]
	private UILabel point;

	[SerializeField]
	private UILabel competitionPoint;

	[SerializeField]
	private UITexture tierIcon;

	[SerializeField]
	private GameObject nationNameWithRank;

	[SerializeField]
	private UILabel prevNationName;

	[SerializeField]
	private UILabelLocalized prevRanking;

	[SerializeField]
	private UISprite prevRankIcon;

	public void UpdateUI(IReuseCellData cell)
	{
	}

	private void UpdateUserUI(RankListItem.CellData cellData)
	{
	}

	private void UpdateCompetitionUI(RankListItem.CompetitionCellData cellData)
	{
	}
}
