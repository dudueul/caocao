using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _RankingRewards : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__RankingReward> data;

	public static _RankingRewards Load()
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
