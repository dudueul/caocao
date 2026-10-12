using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ArenaSynergyLists : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ArenaSynergyList> data;

	public static _ArenaSynergyLists Load()
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
