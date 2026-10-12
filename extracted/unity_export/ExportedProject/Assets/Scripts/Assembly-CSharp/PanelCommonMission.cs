using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class PanelCommonMission : MonoBehaviour
{
	private readonly int[] ID_REWARD_ITEM_EXPLOIT;

	private List<RewardInfo> missionReward;

	private List<RewardInfo> exploitReward;

	private int commonRewardId;

	public void ShowPanel()
	{
	}

	private void ShowComplete(int missionId, JsonData response)
	{
	}

	private void InitMissions()
	{
	}

	private bool InitEpicMission(Transform tf)
	{
		return false;
	}

	private void InitNormalMission(Transform tf)
	{
	}

	private void UpdateMission(Transform tf, Transform tfLock, UserCommonMission mission, bool isEpic)
	{
	}

	private void UpdateChangeMission()
	{
	}

	private void UpdateComplete(int missionId, List<RewardInfo> rewardSet, bool showDesc)
	{
	}

	[DebuggerHidden]
	private IEnumerator UpdateExploitReward(List<RewardInfo> rewardSet)
	{
		return null;
	}

	private void OnCompleteClose()
	{
	}

	private void OnRewardClose()
	{
	}

	private void SetIcon(Transform tf, string iconName)
	{
	}

	private void SetExploitReward(Transform tf, int exploit)
	{
	}

	private void SetGauge(Transform tf, int value, int max_gauge)
	{
	}

	private void SetExploit(Transform tf, int exploit)
	{
	}

	private void SetButtons(Transform tf, bool isComplete, bool isEpic)
	{
	}

	private void SetExploitItems(Transform tf)
	{
	}

	private void SetBG(Transform tf, bool isEpic)
	{
	}

	private void SetMissionInfo(Transform tf, UserCommonMission mission)
	{
	}

	private void SetItemInfo(Transform tf, CommonMission mission)
	{
	}

	private void SetDelegate(Transform tf, EventDelegate callback)
	{
	}
}
