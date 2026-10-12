using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _RelationLists : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__RelationList> data;

	public static _RelationLists Load()
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
