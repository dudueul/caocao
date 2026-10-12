using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _TreasureMixes : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__TreasureMix> data;

	public static _TreasureMixes Load()
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
