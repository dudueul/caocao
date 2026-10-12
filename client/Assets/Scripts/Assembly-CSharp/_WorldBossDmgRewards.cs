using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _WorldBossDmgRewards : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__WorldBossDmgReward> data;

	public static _WorldBossDmgRewards Load()
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
