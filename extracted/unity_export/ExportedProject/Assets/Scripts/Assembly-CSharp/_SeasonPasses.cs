using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _SeasonPasses : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__SeasonPass> data;

	public static _SeasonPasses Load()
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
