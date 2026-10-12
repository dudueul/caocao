using System.Collections.Generic;
using Caocao;

public class PanelMissionInfo : CaocaoUIModalPopup
{
	private bool m_isShowTooltip;

	private bool m_isSkip;

	private float m_CheckTimeInterval;

	private float m_LastTime;

	private bool m_isMenu;

	private bool m_prevPauseState;

	private bool m_isScenarioLobby;

	private List<EventCmdBase> m_duelEvents;

	private bool m_started;

	public const string missionInfoAutoCloseKey = "MISSIONINFO_AUTO_CLOSE";

	public bool isSkip
	{
		get
		{
			return false;
		}
	}

	public static void Show(bool isMenu, bool isScenarioLobby = false)
	{
	}

	public static void Hide()
	{
	}

	public void ShowPanel(bool isMenu, bool isScenarioLobby = false)
	{
	}

	public override void ClosePanel()
	{
	}

	protected override void Awake()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}

	private void Start()
	{
	}

	public override void OnInit()
	{
	}

	private void ExtractInfos()
	{
	}

	private void UpdateBattleTitle()
	{
	}

	private void UpdateWeatherUI()
	{
	}

	private void UpdateTurnUI()
	{
	}

	public void UpdateVictoryConditionUI()
	{
	}

	public void UpdateUI()
	{
	}

	private string GetWeatherSpriteName(Caocao.Weather weatherID)
	{
		return null;
	}

	private void UpdateMissionInfo()
	{
	}

	private void UpdateSealInfo()
	{
	}

	public void ShowWeatherTooltip()
	{
	}

	private void OnClick()
	{
	}

	private string SetMissionStringForBattleMap(int missionId, EnumData.MISSION_STATUS missionState)
	{
		return null;
	}

	private string SetMissionStringForScenarioLobby(int missionId)
	{
		return null;
	}

	private void SetMission()
	{
	}

	public void ToggleAutoClose()
	{
	}

	private void UpdateAutoCloseButton(bool autoClose)
	{
	}

	private void HideAutoClose()
	{
	}
}
