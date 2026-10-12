using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _SecretPlans : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__SecretPlan> data;

	public static _SecretPlans Load()
	{
		return null;
	}

	private void Reset()
	{
	}

	public void Destroy()
	{
	}
}
