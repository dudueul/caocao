using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Clouds : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Cloud> data;

	public static _Clouds Load()
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
