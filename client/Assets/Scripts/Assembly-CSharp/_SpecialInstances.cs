using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _SpecialInstances : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__SpecialInstance> data;

	public static _SpecialInstances Load()
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
