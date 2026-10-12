using UnityEngine;

public class UIRankingRewardListItem : MonoBehaviour
{
	public GameObject[] rankingRewardUIs;

	public UILabelLocalized[] rewardCountUIs;

	public UILabelLocalized[] rewardNameUIs;

	public GameObject[] rankingTitleObjs;

	public UILabelLocalized[] rankingTitles;

	public GameObject selection;

	private bool m_isNewLine;

	private RankingReward m_rankingReward;

	public void InitUI(RankingReward reward, bool isSelection, bool isTitleNewLine = true)
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateTitle()
	{
	}

	private void SetRankingRewardRangeText(UILabelLocalized rankRewardRange)
	{
	}
}
