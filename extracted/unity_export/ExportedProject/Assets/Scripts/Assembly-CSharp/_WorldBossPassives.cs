using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _WorldBossPassives : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__WorldBossPassive> data;

	public static _WorldBossPassives Load()
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
