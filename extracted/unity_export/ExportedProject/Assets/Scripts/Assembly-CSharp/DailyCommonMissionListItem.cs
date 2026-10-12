using System;
using UnityEngine;

public class DailyCommonMissionListItem : MonoBehaviour
{
	private static Color NORMAL_TITLE_COLOR;

	private static Color SEASONPASS_TITLE_COLOR;

	private UserCommonMission m_mission;

	private CommonMission m_permMission;

	private Action<int> onMissionComplete__BackingField;

	private Action onClickMove__BackingField;

	private Action seasonPassLvUpCallback;

	public UserCommonMission mission
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public CommonMission permMission
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private event Action<int> onMissionComplete
	{
		add
		{
		}
		remove
		{
		}
	}

	private event Action onClickMove
	{
		add
		{
		}
		remove
		{
		}
	}

	public void SetData(UserCommonMission userMission, Action<int> callback, Action moveCallBack, Action seasonPassLvUpCallback)
	{
	}

	public void SetData(CommonMission commonMission, Action<int> callback, Action moveCallBack, Action seasonPassLvUpCallback)
	{
	}

	public void OnClickMove()
	{
	}

	public void OnClickComplete()
	{
	}

	public void OnClickSeasonPass()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateGauge(int cur, int max)
	{
	}

	private void AddRewards(int rewardId)
	{
	}
}
