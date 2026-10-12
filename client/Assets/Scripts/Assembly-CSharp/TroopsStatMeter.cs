using UnityEngine;

public class TroopsStatMeter : MonoBehaviour
{
	public TroopStat m_baseStat;

	public TroopStat m_buffStat;

	public PrefabInstance m_atkInst;

	public PrefabInstance m_intInst;

	public PrefabInstance m_defInst;

	public PrefabInstance m_agiInst;

	public PrefabInstance m_morInst;

	public PrefabInstance m_movInst;

	private UIStatProgressBar m_progressAtk;

	private UIStatProgressBar m_progressInte;

	private UIStatProgressBar m_progressDef;

	private UIStatProgressBar m_progressAgi;

	private UIStatProgressBar m_progressMor;

	private UIStatProgressBar m_progressMove;

	private Color GradeBlueColor;

	private Color GradeGreenColor;

	private Color GradeYellowColor;

	private Color GradeGrayColor;

	private Transform atkGrade;

	private Transform intGrade;

	private Transform defGrade;

	private Transform agiGrade;

	private Transform morGrade;

	private UIStatProgressBar progressAtk
	{
		get
		{
			return null;
		}
	}

	private UIStatProgressBar progressInte
	{
		get
		{
			return null;
		}
	}

	private UIStatProgressBar progressDef
	{
		get
		{
			return null;
		}
	}

	private UIStatProgressBar progressAgi
	{
		get
		{
			return null;
		}
	}

	private UIStatProgressBar progressMor
	{
		get
		{
			return null;
		}
	}

	private UIStatProgressBar progressMove
	{
		get
		{
			return null;
		}
	}

	private void OnEnable()
	{
	}

	private void OnDestroy()
	{
	}

	public void UpdateStat()
	{
	}

	public void UpdateStatImmediately()
	{
	}

	public void SetUserUnit(UserUnit userUnit)
	{
	}

	public void SetUserUnitSimplifyStat(UserUnit userUnit)
	{
	}

	public void SetUserUnitStat(UserUnit userUnit)
	{
	}

	private void SetMaxStat(JobType jobType)
	{
	}

	public void SetStats(TroopStat baseStat, TroopStat buffStat)
	{
	}

	public void SetStats(TroopStat baseStat, TroopStat buffStat, int move)
	{
	}

	public void SetStatsImmediately(TroopStat baseStat, TroopStat buffStat)
	{
	}

	public void SetNextStats(UserUnit userUnit)
	{
	}

	public void SetBuffStat(TroopStat buffStat)
	{
	}

	private void UpdateGrade(UserUnit userUnit)
	{
	}

	private void SetGrade(Transform ui, string grade)
	{
	}
}
