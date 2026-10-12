using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class DuelCmdDialogue : DuelCmdBase
{
	private bool isOurUnit;

	private string dialogString;

	private bool isAfterDelay;

	private bool isWaiting;

	private float waitDelayTime;

	private int faceType;

	private EnumData.DialogType typeParam;

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
