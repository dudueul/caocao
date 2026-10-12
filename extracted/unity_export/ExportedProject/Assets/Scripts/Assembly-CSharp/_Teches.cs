using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Teches : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Tech> data;

	public static _Teches Load()
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
