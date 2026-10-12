using System;
using UnityEngine;

public class PanelMissionSpecialListItem : MonoBehaviour
{
	private CommonMission m_mission;

	private UserCommonMission m_userMission;

	private Action<int> onMissionComplete__BackingField;

	public CommonMission mission
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

	public void SetData(CommonMission commonMission, Action<int> callback)
	{
	}

	public void OnClickComplete()
	{
	}

	private void UpdateUI()
	{
	}
}
