using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public abstract class DuelAttackBase : MonoBehaviour
{
	protected bool m_isCompleted;

	protected bool m_isPlaying;

	protected GameObject m_Attacker;

	protected GameObject m_Defender;

	protected bool m_isOurUnit;

	protected Queue<DuelCmdDuelAction> m_duelActions;

	private List<DuelCmdDuelAction> m_processingActions;

	protected Queue<float> m_cmdWaitTimes;

	public bool isCompleted
	{
		get
		{
			return false;
		}
	}

	public bool isPlaying
	{
		get
		{
			return false;
		}
	}

	public GameObject Attacker
	{
		get
		{
			return null;
		}
	}

	public GameObject Defender
	{
		get
		{
			return null;
		}
	}

	public bool isOurUnit
	{
		get
		{
			return false;
		}
	}

	public abstract void SetData(params object[] args);

	public void StartAnimation()
	{
	}

	[DebuggerHidden]
	private IEnumerator Animate()
	{
		return null;
	}
}
