using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _SeasonPassRewards : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__SeasonPassReward> data;

	public static _SeasonPassRewards Load()
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
