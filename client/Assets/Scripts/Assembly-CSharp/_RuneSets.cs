using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _RuneSets : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__RuneSet> data;

	public static _RuneSets Load()
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
