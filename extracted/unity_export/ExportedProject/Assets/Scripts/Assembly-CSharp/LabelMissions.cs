using System.Collections.Generic;
using UnityEngine;

public class LabelMissions : SelfRegister2UIComponentsMonobehaviour
{
	private List<EventCmdBase> m_duelEvents;

	private bool updateTable;

	private void Start()
	{
	}

	private string SetMissionString(int missionId, EnumData.MISSION_STATUS missionState)
	{
		return null;
	}

	private void UpdateMissionUI()
	{
	}

	private void OnEnable()
	{
	}

	private void LateUpdate()
	{
	}

	public void UpdateMissionUIOnFinish()
	{
	}

	private void MarkMission(Transform targetUI, UILabelLocalized targetLabelUI, EnumData.MISSION_STATUS status)
	{
	}

	private void OnStatusChangedHandler(ControlFlow.statusType status)
	{
	}

	protected override void OnDestroy()
	{
	}
}
