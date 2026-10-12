using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class DuelCmdDie : DuelCmdBase
{
	private bool isEnemyUnit;

	private float aniDuration;

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
