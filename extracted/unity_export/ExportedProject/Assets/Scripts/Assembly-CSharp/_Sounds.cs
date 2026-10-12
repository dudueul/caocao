using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Sounds : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Sound> data;

	public static _Sounds Load()
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
