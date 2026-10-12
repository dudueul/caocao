using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _RewardRanks : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__RewardRank> data;

	public static _RewardRanks Load()
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
