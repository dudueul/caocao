using System.Collections.Generic;

public class LabelMissionsScenarioMenu : SelfRegister2UIComponentsMonobehaviour
{
	private const string markOn = "mission_seal_s";

	private const string markOff = "mission_seal_d";

	public List<UISprite> marks;

	public List<UILabelLocalized> missionInfos;

	private List<EventCmdBase> m_duelEvents;

	public static void Toggle(bool on)
	{
	}

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	private string SetMissionString(int missionId)
	{
		return null;
	}
}
