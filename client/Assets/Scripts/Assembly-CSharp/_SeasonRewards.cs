using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _SeasonRewards : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__SeasonReward> data;

	public static _SeasonRewards Load()
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
