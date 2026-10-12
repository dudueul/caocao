using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _SelectBoxes : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__SelectBox> data;

	public static _SelectBoxes Load()
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
