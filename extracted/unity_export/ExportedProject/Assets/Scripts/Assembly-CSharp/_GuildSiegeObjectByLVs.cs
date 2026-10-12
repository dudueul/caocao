using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildSiegeObjectByLVs : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildSiegeObjectByLV> data;

	public static _GuildSiegeObjectByLVs Load()
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
