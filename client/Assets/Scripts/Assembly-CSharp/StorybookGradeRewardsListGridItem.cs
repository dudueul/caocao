using UnityEngine;

public class StorybookGradeRewardsListGridItem : MonoBehaviour
{
	[SerializeField]
	private UILabelLocalized gradeLabel;

	[SerializeField]
	private Transform rewardUIParent;

	[SerializeField]
	private UILabelLocalized itemName;

	[SerializeField]
	private Transform dim;

	private int needGradeCount;

	private RewardInfo rewardInfo;

	private void OnDestroy()
	{
	}

	public void UpdateUI(int gradeCount, RewardInfo rewardInfo, bool completed)
	{
	}
}
