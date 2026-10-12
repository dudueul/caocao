using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ArenaPresets : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ArenaPreset> data;

	public static _ArenaPresets Load()
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
