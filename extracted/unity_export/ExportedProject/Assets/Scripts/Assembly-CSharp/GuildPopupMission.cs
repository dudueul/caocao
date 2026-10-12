using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class GuildPopupMission : CaocaoUIModalPopup
{
	public enum MissionType
	{
		PrevWeekly = 0,
		Weekly = 1,
		Count = 2
	}

	public UIGrid gridMission;

	public UIScrollView scrollViewMission;

	public UILabelLocalized labelMissionNum;

	public UILabelLocalized labelRemainTime;

	public UILabelLocalized labelMissionType;

	public UIButton btnMissionType;

	public MissionType curMissionType;

	private bool handleError;

	private bool tryingGetMissions;

	private Coroutine tryGetNewMissionsCoroutine;

	private Coroutine updateRemainTimeCoroutine;

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

	public static void Show()
	{
	}

	private void RemoveAll()
	{
	}

	private void OnUpdateGuildMissions()
	{
	}

	private void OnUpdatePrevWeekGuildMissions()
	{
	}

	private void OnGuildError(string error, ref bool handled)
	{
	}

	[DebuggerHidden]
	private IEnumerator UpdateRemainTime()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator TryGetNewMissions()
	{
		return null;
	}

	private Dictionary<int, GuildMission> GetId2Missions(MissionType type)
	{
		return null;
	}

	public void UpdateMissionUI(MissionType type)
	{
	}

	public void UpdateButtonState(MissionType type)
	{
	}

	public void UpdateTimeText(MissionType type)
	{
	}

	public void OnClickBtnMissionType()
	{
	}

	protected override void OnDestroy()
	{
	}
}
