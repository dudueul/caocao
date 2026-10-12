using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ArenaScenarios : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ArenaScenario> data;

	public static _ArenaScenarios Load()
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
