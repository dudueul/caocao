using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _TileJobCosts : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__TileJobCost> data;

	public static _TileJobCosts Load()
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
