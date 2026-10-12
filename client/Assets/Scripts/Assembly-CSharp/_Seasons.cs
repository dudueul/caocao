using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Seasons : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Season> data;

	public static _Seasons Load()
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
