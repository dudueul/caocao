using UnityEngine;

public class PanelMainMissions : MonoBehaviour
{
	private enum missionType
	{
		COMMON_MISSION = 0,
		SCENARIO_MISSION = 1
	}

	private missionType m_missionType;

	private void Start()
	{
	}

	private void OnUpdatePanel()
	{
	}

	private void UpdateCommonMission(bool turnOn)
	{
	}

	private void _UpdateCommonMission(Transform tf)
	{
	}

	private void __UpdateCommonMission(Transform tf, UserCommonMission mission)
	{
	}

	private void UpdateScenarioMission(bool turnOn)
	{
	}

	private void _UpdateScenarioMission(Transform tf)
	{
	}

	private void __UpdateScenarioMission(Transform tf, int missionId, int sequence)
	{
	}

	private void SetMissionSeal(Transform tf, int grade)
	{
	}

	private void SetUILabel(Transform tf, string path, string message)
	{
	}

	private void SetGauge(Transform tf, UserCommonMission mission, int max_gauge)
	{
	}

	private void SetCheckBox(Transform tf, bool isComplete, int sequence)
	{
	}

	private void SetBG(Transform tf, Color32 color)
	{
	}

	public void OnClickCommonMission()
	{
	}

	public void OnClickScenarioMission()
	{
	}
}
