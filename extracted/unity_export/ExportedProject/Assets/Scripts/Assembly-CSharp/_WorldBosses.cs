using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _WorldBosses : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__WorldBoss> data;

	public static _WorldBosses Load()
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
