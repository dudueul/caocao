using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class TimeBoxOpenBasicReward : MonoBehaviour
{
	[SerializeField]
	private UISprite bg;

	[SerializeField]
	private UIGrid grid;

	private Vector3 itemUIScale;

	private int itemUIDepth;

	public void UpdateUI(JsonData rewardSet)
	{
	}

	private void InstanciateRewardItemUI(JsonData rewardSet)
	{
	}

	private void InstanciateRewardItemUI(List<RewardInfo> rewardSet)
	{
	}

	private void ClearGrid()
	{
	}

	private void ResizeBgWidth()
	{
	}
}
