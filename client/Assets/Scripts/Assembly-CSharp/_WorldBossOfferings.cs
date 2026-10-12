using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _WorldBossOfferings : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__WorldBossOffering> data;

	public static _WorldBossOfferings Load()
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
