using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _NewThousandPassives : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__NewThousandPassive> data;

	public static _NewThousandPassives Load()
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
