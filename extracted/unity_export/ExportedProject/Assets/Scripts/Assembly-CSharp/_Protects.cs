using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Protects : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Protect> data;

	public static _Protects Load()
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
