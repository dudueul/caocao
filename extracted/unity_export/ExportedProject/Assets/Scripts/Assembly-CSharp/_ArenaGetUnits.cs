using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ArenaGetUnits : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ArenaGetUnit> data;

	public static _ArenaGetUnits Load()
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
