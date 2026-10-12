using UnityEngine;

public class PanelAchievementCommonMission : CaocaoUIModalPopup
{
	private enum Tab
	{
		CommonMission = 0,
		Achievement = 1
	}

	[SerializeField]
	private CaocaoTabButtons tabButtons;

	private bool m_init;

	protected override void Awake()
	{
	}

	private void Start()
	{
	}

	protected override void OnDestroy()
	{
	}

	public static void ShowMainPanel()
	{
	}

	public static void ShowAchievementPanel()
	{
	}

	private void Init()
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

	public void UpdateUI()
	{
	}

	private void _ShowCommonMissionPanel(bool turnOn)
	{
	}

	private void _ShowAchievementPanel(bool turnOn)
	{
	}

	private void OnSetCommonMissionAlram()
	{
	}

	private void OnSetAchievementAlram()
	{
	}
}
