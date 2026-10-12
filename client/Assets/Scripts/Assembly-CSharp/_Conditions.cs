using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Conditions : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Condition> data;

	public static _Conditions Load()
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
