using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _SpawnerSets : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__SpawnerSet> data;

	public static _SpawnerSets Load()
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
