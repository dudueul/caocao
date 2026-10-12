using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _RewardLvs : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__RewardLv> data;

	public static _RewardLvs Load()
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
