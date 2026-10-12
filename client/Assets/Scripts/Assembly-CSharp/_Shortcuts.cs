using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Shortcuts : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Shortcut> data;

	public static _Shortcuts Load()
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
