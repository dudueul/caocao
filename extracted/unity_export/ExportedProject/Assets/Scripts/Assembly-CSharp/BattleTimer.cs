using System;
using UnityEngine;

public class BattleTimer : SelfRegister2UIComponentsMonobehaviour
{
	public UILabelLocalized timeLabel;

	private bool m_timeOver;

	private DateTime m_expireTime;

	private float m_remainSec;

	private Transform competitionTimer;

	private UISlider competitionOwnSlider;

	private UISlider competitionEnemySlider;

	private int totalSeconds;

	private int m_competitionWarningMinutes;

	private int m_competitionWarningSeconds;

	private bool m_competitionOpponentWait;

	[HideInInspector]
	public int alarmSec;

	public Action<int> OnAlarm;

	public Action OnTimeOver;

	private int m_prevMinutes;

	private int m_prevSeconds;

	public bool timeOver
	{
		get
		{
			return false;
		}
	}

	public DateTime expireTime
	{
		get
		{
			return default(DateTime);
		}
	}

	public int remainSec
	{
		get
		{
			return 0;
		}
	}

	public float remainSecFloat
	{
		get
		{
			return 0f;
		}
	}

	public bool competitionOpponentWait
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private void Init()
	{
	}

	public void StartTimer(DateTime expireTime, bool init = true, bool opponentWait = false)
	{
	}

	public void StopTimer()
	{
	}

	public void AddAlarm(int alarmSec, Action<int> callback)
	{
	}

	private void UpdateTime()
	{
	}

	private void UpdateCompetitionSlider(float time)
	{
	}

	private void Update()
	{
	}

	private void OnApplicationPause(bool pauseState)
	{
	}

	private void OnDestory()
	{
	}

	private void UpdateConnectionInfo(ulong uid, EnumData.MultiplayUserState state, int memberCnt)
	{
	}
}
