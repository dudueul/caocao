using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class CommonMissionInfo : MonoBehaviour
{
	private UserCommonMission m_mission;

	public Action<int, JsonData> OnCommonMissionUpdate;

	public Action OnCommonMissionChange;

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

	public void OnChangeMissionClicked()
	{
	}

	public void OnCompleteMission()
	{
	}

	private void ShowChangeMission(int availableCount)
	{
	}

	public PanelPopupBase.PopupState RequestChangeMission()
	{
		return PanelPopupBase.PopupState.KeepOpen;
	}

	private void OnCompleteMissionCallback(JsonData response)
	{
	}

	private void GetResponse(JsonData rewards, ref List<int> result)
	{
	}

	private void OnChangeMissionCallback(JsonData response)
	{
	}
}
