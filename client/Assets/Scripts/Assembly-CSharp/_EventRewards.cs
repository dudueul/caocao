using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _EventRewards : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__EventReward> data;

	public static _EventRewards Load()
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
