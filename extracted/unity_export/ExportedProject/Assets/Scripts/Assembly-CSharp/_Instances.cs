using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Instances : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Instance> data;

	public static _Instances Load()
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
