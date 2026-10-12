using UnityEngine;

public class GuildUIGuildHistoryListItem : MonoBehaviour
{
	public UILabelLocalized timeLabel;

	public UILabelLocalized contextLabel;

	public UIButton raidRankingBtn;

	private GuildHistory guildHistory;

	public static GuildUIGuildHistoryListItem InstantiateOn(Transform parent)
	{
		return null;
	}

	public void SetGuildHistory(GuildHistory guildHistory)
	{
	}

	public void OnRaidRankingBtnClicked()
	{
	}
}
