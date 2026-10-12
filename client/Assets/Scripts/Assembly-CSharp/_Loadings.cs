using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Loadings : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Loading> data;

	public static _Loadings Load()
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
