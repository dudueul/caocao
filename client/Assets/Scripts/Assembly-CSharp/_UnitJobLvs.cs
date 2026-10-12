using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _UnitJobLvs : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__UnitJobLv> data;

	public static _UnitJobLvs Load()
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
