using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _WorldBossTiers : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__WorldBossTier> data;

	public static _WorldBossTiers Load()
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
