using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ScenarioFlags : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ScenarioFlag> data;

	public static _ScenarioFlags Load()
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
