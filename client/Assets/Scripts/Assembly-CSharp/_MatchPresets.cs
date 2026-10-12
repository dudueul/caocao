using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _MatchPresets : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__MatchPreset> data;

	public static _MatchPresets Load()
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
