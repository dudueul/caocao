using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class DuelFuncInstantiateEffect : DuelActionBase
{
	private string m_effectType;

	public override void SetData(EnumData.DuelAction action, string[] args)
	{
	}

	public override void StartAnimation()
	{
	}

	[DebuggerHidden]
	private IEnumerator Animate()
	{
		return null;
	}

	private GameObject GetEffectResource(string effectType)
	{
		return null;
	}
}
