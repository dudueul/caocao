using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Heros : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Hero> data;

	public static _Heros Load()
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
