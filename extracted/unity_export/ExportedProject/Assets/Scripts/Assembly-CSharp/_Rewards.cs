using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Rewards : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Reward> data;

	public static _Rewards Load()
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
