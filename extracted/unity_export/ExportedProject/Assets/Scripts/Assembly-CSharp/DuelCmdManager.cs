using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class DuelCmdManager : MonoBehaviour
{
	private Queue<DuelCmdBase> m_CmdQueue;

	public Action OnProcessSarted;

	public Action OnProcessFinished;

	public bool m_isStepwiseMode;

	private bool m_doProcessCommand;

	public void doProcessCommandSetTrue()
	{
	}

	private void Start()
	{
	}

	[DebuggerHidden]
	public IEnumerator StartProcessCommand()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ProcessCommand(DuelCmdBase cmd)
	{
		return null;
	}

	public DuelCmdBase Peek()
	{
		return null;
	}

	public DuelCmdBase Dequeue()
	{
		return null;
	}

	public void Enqueue(DuelCmdBase newCmd)
	{
	}

	public void Clear()
	{
	}

	public void MakeNewCommand(EnumData.DuelCommands cmdType, params object[] args)
	{
	}

	public List<string> GetCommandsNamesInQueue()
	{
		return null;
	}
}
