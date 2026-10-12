using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ArenaNPCTreasures : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ArenaNPCTreasure> data;

	public static _ArenaNPCTreasures Load()
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
