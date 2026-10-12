using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ArenaNPCSettings : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ArenaNPCSetting> data;

	public static _ArenaNPCSettings Load()
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
