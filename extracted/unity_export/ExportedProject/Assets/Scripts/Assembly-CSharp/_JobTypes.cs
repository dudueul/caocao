using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _JobTypes : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__JobType> data;

	public static _JobTypes Load()
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
