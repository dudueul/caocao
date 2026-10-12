using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _JobRelations : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__JobRelation> data;

	public static _JobRelations Load()
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
