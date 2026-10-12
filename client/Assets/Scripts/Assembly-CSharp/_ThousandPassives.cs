using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ThousandPassives : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ThousandPassive> data;

	public static _ThousandPassives Load()
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
