using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class LabelTurnSide : SelfRegister2UIComponentsMonobehaviour
{
	private const string competitionTurnPoolName = "competitionTurn";

	private const string brawlTurnPoolName = "BrawlTurn";

	private int showUITurnIndex;

	private void Start()
	{
	}

	public Coroutine Animate()
	{
		return null;
	}

	public void SkipTurn(int turn)
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowTrunInfo()
	{
		return null;
	}

	private GameObject MakeCompetitionTurnObj()
	{
		return null;
	}

	private GameObject MakeBrawlTurnObj()
	{
		return null;
	}

	private void BrawlTurnDescText(UILabelLocalized descLabel)
	{
	}

	private void BrawlTurnInfoText(UILabelLocalized infoLabel)
	{
	}
}
