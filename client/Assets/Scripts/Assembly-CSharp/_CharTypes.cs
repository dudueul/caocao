using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _CharTypes : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__CharType> data;

	public static _CharTypes Load()
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
