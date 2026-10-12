using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _NPCTreasureSettings : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__NPCTreasureSetting> data;

	public static _NPCTreasureSettings Load()
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
