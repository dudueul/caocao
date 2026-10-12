using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Units : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Unit> data;

	public static _Units Load()
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
