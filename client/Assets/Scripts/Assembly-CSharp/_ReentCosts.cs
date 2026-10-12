using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ReentCosts : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ReentCost> data;

	public static _ReentCosts Load()
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
