using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _DigitValues : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__DigitValue> data;

	public static _DigitValues Load()
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
