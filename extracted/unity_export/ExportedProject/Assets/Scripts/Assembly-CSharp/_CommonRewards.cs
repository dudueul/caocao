using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _CommonRewards : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__CommonReward> data;

	public static _CommonRewards Load()
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
