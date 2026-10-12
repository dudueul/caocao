using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class DuelCmdAppearHero : DuelCmdBase
{
	private bool isOurUnit;

	private string dialogString;

	private int faceType;

	private EnumData.DialogType typeParam;

	private EnumData.DuelAction duelAction;

	private DuelCmdDuelAction duelCmdAction;

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
