using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _UnitTreeNodes : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__UnitTreeNode> data;

	public static _UnitTreeNodes Load()
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
