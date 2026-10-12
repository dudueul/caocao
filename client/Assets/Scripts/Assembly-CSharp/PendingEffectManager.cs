using System.Collections.Generic;
using UnityEngine;

public class PendingEffectManager : Singleton<PendingEffectManager>
{
	public delegate GameObject EffectCallAction();

	private List<EffectCallAction> m_effectCalls;

	private GameObject m_currEffect;

	public bool isOn
	{
		get
		{
			return false;
		}
	}

	public void RegisterEffectCall(EffectCallAction effectCall, bool atFirst = false)
	{
	}

	public void CallEffect()
	{
	}

	public void CallNext()
	{
	}
}
