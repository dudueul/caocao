using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ArenaNPCPassives : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ArenaNPCPassive> data;

	public static _ArenaNPCPassives Load()
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
