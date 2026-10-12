using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _SetTimeBoxes : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__SetTimeBox> data;

	public static _SetTimeBoxes Load()
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
