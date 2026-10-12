using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _HotTimeRewards : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__HotTimeReward> data;

	public static _HotTimeRewards Load()
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
