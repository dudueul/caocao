using UnityEngine;

public class UIResultRewardListItem : MonoBehaviour
{
	public UILabelLocalized tierName;

	public UILabelLocalized tierPoint;

	public UISprite tierIcon;

	public GameObject rewardParent;

	public UILabelLocalized rewardName;

	public UILabelLocalized rewardCount;

	public GameObject selection;

	private Tier m_tier;

	public void InitUI(Tier tier, bool isSelection)
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdatePoint()
	{
	}

	private void UpdateInfo(RewardInfo info)
	{
	}
}
