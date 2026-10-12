using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _TierUps : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__TierUp> data;

	public static _TierUps Load()
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
