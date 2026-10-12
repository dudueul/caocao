using System;
using System.Collections;
using System.Diagnostics;
using HutongGames.PlayMaker;
using UnityEngine;

public class UIDirectingLiftTime : MonoBehaviour
{
	public bool isDirectingDone;

	public Action OnDirectionEnd;

	[SerializeField]
	private bool m_useLifeTime;

	[SerializeField]
	private float m_lifeTime;

	[SerializeField]
	private PlayMakerFSM m_fsm;

	[SerializeField]
	private string m_endStateName;

	private FsmState m_targetState;

	private float m_startTime;

	public bool useLifeTime
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public float lifeTime
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public PlayMakerFSM fsm
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string endStateName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void Update()
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitStateTillEnd()
	{
		return null;
	}
}
