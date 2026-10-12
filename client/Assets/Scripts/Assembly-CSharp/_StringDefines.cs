using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _StringDefines : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__StringDefine> data;

	public static _StringDefines Load()
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
