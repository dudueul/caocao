using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ExpandPassiveMelees : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ExpandPassiveMelee> data;

	public static _ExpandPassiveMelees Load()
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
