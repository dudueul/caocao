using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _TimeBoxes : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__TimeBox> data;

	public static _TimeBoxes Load()
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
