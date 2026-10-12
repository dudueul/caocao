using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Achievements : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Achievement> data;

	public static _Achievements Load()
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
