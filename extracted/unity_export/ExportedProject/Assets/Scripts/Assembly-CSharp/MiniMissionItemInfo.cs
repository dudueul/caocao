using System;
using LitJson;
using UnityEngine;

public class MiniMissionItemInfo : MonoBehaviour
{
	private UserCommonMission m_mission;

	public Action OnClickedComplete;

	public Action<int, JsonData> OnMissionComplete;

	private bool m_clickHandlerInitialized;

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

	private void Start()
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

	private void OnCompleteMissionCallback(JsonData response)
	{
	}

	private void UpdateUI()
	{
	}

	private void SetButtons(bool isComplete, CommonMission mission)
	{
	}

	private void InitButtonsClickHandler()
	{
	}

	private void OnDestroy()
	{
	}
}
