using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _NewThousandUnits : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__NewThousandUnit> data;

	public static _NewThousandUnits Load()
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
