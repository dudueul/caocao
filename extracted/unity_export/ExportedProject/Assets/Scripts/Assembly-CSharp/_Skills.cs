using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Skills : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Skill> data;

	public static _Skills Load()
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
