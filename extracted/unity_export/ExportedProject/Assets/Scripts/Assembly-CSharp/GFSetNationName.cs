using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class GFSetNationName : GameFlowNodeBase
{
	private GameObject m_opening;

	protected override void DefineFollowers()
	{
	}

	protected override int? _GetState()
	{
		return null;
	}

	protected override void _DoLogic()
	{
	}

	public void SetNationNameCallback()
	{
	}

	[DebuggerHidden]
	private IEnumerator GoToNextStepCoroutine()
	{
		return null;
	}
}
