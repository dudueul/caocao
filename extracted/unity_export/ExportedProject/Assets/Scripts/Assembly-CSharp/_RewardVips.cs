using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _RewardVips : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__RewardVip> data;

	public static _RewardVips Load()
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
