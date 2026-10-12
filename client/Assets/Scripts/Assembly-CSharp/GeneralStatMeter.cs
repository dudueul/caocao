using UnityEngine;

public class GeneralStatMeter : MonoBehaviour
{
	public GeneralStat m_baseStat;

	public GeneralStat m_buffStat;

	public PrefabInstance m_strInst;

	public PrefabInstance m_wisInst;

	public PrefabInstance m_chaInst;

	public PrefabInstance m_dexInst;

	public PrefabInstance m_luckInst;

	private UIStatProgressBar m_progressStr;

	private UIStatProgressBar m_progressWis;

	private UIStatProgressBar m_progressCha;

	private UIStatProgressBar m_progressDex;

	private UIStatProgressBar m_progressLuck;

	public UIStatProgressBar progressStr
	{
		get
		{
			return null;
		}
	}

	public UIStatProgressBar progressWis
	{
		get
		{
			return null;
		}
	}

	public UIStatProgressBar progressCha
	{
		get
		{
			return null;
		}
	}

	public UIStatProgressBar progressDex
	{
		get
		{
			return null;
		}
	}

	public UIStatProgressBar progressLuck
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

	public void SetStats(GeneralStat baseStat, GeneralStat buffStat)
	{
	}

	public void SetStatsImmediately(GeneralStat baseStat, GeneralStat buffStat)
	{
	}

	public void SetBuffStat(GeneralStat buffStat)
	{
	}
}
