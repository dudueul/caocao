using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _RunePassiveExps : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__RunePassiveExp> data;

	public static _RunePassiveExps Load()
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
