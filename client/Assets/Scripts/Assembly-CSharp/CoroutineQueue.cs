using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class CoroutineQueue
{
	private readonly uint maxActive;

	private readonly Func<IEnumerator, Coroutine> coroutineStarter;

	private readonly Queue<IEnumerator> queue;

	private uint numActive;

	public CoroutineQueue(uint maxActive, Func<IEnumerator, Coroutine> coroutineStarter)
	{
	}

	public void Run(IEnumerator coroutine)
	{
	}

	[DebuggerHidden]
	private IEnumerator CoroutineRunner(IEnumerator coroutine)
	{
		return null;
	}
}
