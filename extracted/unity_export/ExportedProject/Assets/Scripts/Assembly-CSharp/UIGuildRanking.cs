using UnityEngine;

public class UIGuildRanking : MonoBehaviour
{
	[SerializeField]
	private GuildUIMarkName guildInfoComp;

	[SerializeField]
	private UILabel nationName;

	[SerializeField]
	private UILabel point;

	[SerializeField]
	private GameObject prevRankObj;

	[SerializeField]
	private UILabelLocalized prevRanking;

	[SerializeField]
	private UISprite prevRankIcon;

	public void UpdateUI(IReuseCellData cell)
	{
	}
}
