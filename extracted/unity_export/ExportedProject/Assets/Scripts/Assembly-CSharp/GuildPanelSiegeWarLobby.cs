using System;
using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class GuildPanelSiegeWarLobby : PanelSelfRegister2UIComponentsMonobehaviour
{
	private enum Tab
	{
		LeagueInfo = 0,
		WeeklyRecord = 1,
		DefenceStatus = 2,
		Num = 3
	}

	[Serializable]
	public class FlagUI
	{
		public ItemUI itemUI;

		public UILabelLocalized labelName;
	}

	public GuildUIMarkName guildMark;

	public UILabel labelGuildScore;

	public UILabel labelMyScore;

	public FlagUI guildFlag;

	public FlagUI nationFlag;

	public CaocaoTabButtons tabButton;

	public GuildSiegeUITabPage[] tabPages;

	public GameObject[] dims;

	public UILabelLocalized[] dimTimeLabel;

	private Tab currentTab;

	private const int timeRemainedRefreshDuration = 1;

	public UILabelLocalized timeRemainedLabel;

	public UILabelLocalized participationLabel;

	public Transform atkBtn;

	public Transform applyBtn;

	public override void ShowPanel()
	{
	}

	public override void ClosePanel()
	{
	}

	public static void Show()
	{
	}

	public void ShowRankingButton()
	{
	}

	public void ShowRewardInfoButton()
	{
	}

	public void GoSiegeWarButton()
	{
	}

	public void StartSiegeWarButton()
	{
	}

	public void OnTabChanged(CaocaoButton button)
	{
	}

	public void OnClickApplyGuildSiegeWarBtn()
	{
	}

	[DebuggerHidden]
	private IEnumerator RequestGuildSiegeWarInfo()
	{
		return null;
	}

	private void UpdateUI()
	{
	}

	private void UpdateMyGuildInfo()
	{
	}

	private void UpdateDim(GuildSiegeWarSchedule schedule)
	{
	}

	private void ShowDim(bool show, int idx = 0, string str = null, string timeRemained = null)
	{
	}

	private void SetActiveTabs(bool active)
	{
	}

	private void UpdateBottom(GuildSiegeWarSchedule schedule)
	{
	}

	private void UpdateTimeRemainedLabel(string str, DateTime time)
	{
	}

	public void AddTimer()
	{
	}

	public void RemoveTimer()
	{
	}

	public void UpdateByTimer()
	{
	}
}
