using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class ConsecutiveAttendanceListItem : MonoBehaviour
{
	[SerializeField]
	private UILabelLocalized m_title;

	[SerializeField]
	private UITexture m_gold;

	[SerializeField]
	private UILabelLocalized m_goldValue;

	[SerializeField]
	private UIGrid m_grid;

	[SerializeField]
	private UIButton m_recieveBtn;

	[SerializeField]
	private GameObject m_clear;

	private Action m_callback;

	private static readonly Vector3 rewardScale;

	public DailyGift dailyGift { get; private set; }

	public List<RewardInfo> rewards { get; private set; }

	public bool isRecieved
	{
		get
		{
			return false;
		}
	}

	public bool canGiveReward
	{
		get
		{
			return false;
		}
	}

	public void Initialize(DailyGift pdData, Action callback)
	{
	}

	public void UpdateUI()
	{
	}

	public void OnClickGetReward()
	{
	}

	private void OnGetReward(JsonData response)
	{
	}
}
