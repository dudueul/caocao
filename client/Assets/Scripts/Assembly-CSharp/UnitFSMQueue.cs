using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using HutongGames.PlayMaker;
using UnityEngine;

public class UnitFSMQueue : MonoBehaviour
{
	private const string ms_battleUnitFsmPath = "PlayMaker/Templates/newBattlemapUnitAnimFsm";

	private const string ms_eventUnitFsmPath = "PlayMaker/Templates/newEventUnitAnimFSM";

	private bool isInitialized;

	private PlayMakerFSM fsm;

	private Queue<FSMQueueElement> FSMStates;

	private FsmString queueElemState;

	private FsmInt queueElemSrl;

	private FsmBool queueElemIgnoreTrigger;

	public bool ignoreEnqueue { get; set; }

	private void Start()
	{
	}

	public void SelectFsmTemplate()
	{
	}

	public bool IsEmpty()
	{
		return false;
	}

	public FSMQueueElement Enqueue(string stateName, Action callback, bool ignoreTrigger = false, float postDelay = 0f)
	{
		return null;
	}

	public void Next()
	{
	}

	public void Consume(int srl)
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitPostDelay(float postDelay)
	{
		return null;
	}

	public void ConsumeAll()
	{
	}
}
