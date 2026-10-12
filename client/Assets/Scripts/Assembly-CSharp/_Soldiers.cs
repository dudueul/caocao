using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Soldiers : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Soldier> data;

	public static _Soldiers Load()
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
