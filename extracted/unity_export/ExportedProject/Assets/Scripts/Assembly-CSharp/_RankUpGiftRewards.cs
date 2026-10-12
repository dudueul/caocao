using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _RankUpGiftRewards : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__RankUpGiftReward> data;

	public static _RankUpGiftRewards Load()
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
