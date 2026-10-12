using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class VipLvRewardListItem : MonoBehaviour
{
	public Transform m_lvTitle;

	public Transform m_rewardTransf;

	public Transform m_btnVipReward;

	public Transform m_completed;

	public Transform m_grid;

	private Vector3 rewardPrefabScale;

	private List<RewardInfo> rewards;

	private int vipLevel;

	private string explain;

	private bool waitRequest;

	private bool isTreasure;

	public void SetData(int vipLevel, string explain)
	{
	}

	public void UpdateUI()
	{
	}

	private void UpdateExpalin()
	{
	}

	private void UpdateRewardInfo()
	{
	}

	private void UpdateReceiveButton()
	{
	}

	public void OnReceiveButtonClick()
	{
	}

	private void OnCompleteCallback(JsonData response)
	{
	}

	private void ShowComplete(List<RewardInfo> rewardInfos)
	{
	}
}
