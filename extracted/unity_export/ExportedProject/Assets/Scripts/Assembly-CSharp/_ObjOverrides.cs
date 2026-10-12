using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ObjOverrides : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ObjOverride> data;

	public static _ObjOverrides Load()
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
