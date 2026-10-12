using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Faces : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Face> data;

	public static _Faces Load()
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
