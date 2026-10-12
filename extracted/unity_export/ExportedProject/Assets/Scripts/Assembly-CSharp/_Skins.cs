using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Skins : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Skin> data;

	public static _Skins Load()
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
