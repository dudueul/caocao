using System.Collections;
using System.Diagnostics;
using Caocao;
using UnityEngine;

public class DuelCmdTurnBack : DuelCmdBase
{
	private UnitDirection foward
	{
		get
		{
			return UnitDirection.back;
		}
		set
		{
		}
	}

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
