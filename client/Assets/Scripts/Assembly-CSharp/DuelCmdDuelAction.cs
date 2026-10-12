using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class DuelCmdDuelAction : DuelCmdBase
{
	private EnumData.DuelAction duelAction;

	private bool isOurUnit;

	private bool isWaiting;

	private List<string> m_spcArgs;

	private GameObject Owner
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private GameObject Enemy
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public override void SetData(object[] args)
	{
	}

	[DebuggerHidden]
	public override IEnumerator ProcessCommand()
	{
		return null;
	}

	public override void OnCommandStarted()
	{
	}

	public override void OnCommandFinished()
	{
	}
}
