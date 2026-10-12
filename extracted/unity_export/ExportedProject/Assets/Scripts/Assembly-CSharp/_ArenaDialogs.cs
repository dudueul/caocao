using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ArenaDialogs : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ArenaDialog> data;

	public static _ArenaDialogs Load()
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
