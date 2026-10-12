using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _WorldBossSeasons : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__WorldBossSeason> data;

	public static _WorldBossSeasons Load()
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
