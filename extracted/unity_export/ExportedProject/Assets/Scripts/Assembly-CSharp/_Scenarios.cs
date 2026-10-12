using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Scenarios : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Scenario> data;

	public static _Scenarios Load()
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
