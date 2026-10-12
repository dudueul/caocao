using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Vips : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Vip> data;

	public static _Vips Load()
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
