using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Towers : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Tower> data;

	public static _Towers Load()
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
