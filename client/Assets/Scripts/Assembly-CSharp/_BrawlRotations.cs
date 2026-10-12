using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _BrawlRotations : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__BrawlRotation> data;

	public static _BrawlRotations Load()
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
