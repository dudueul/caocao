using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ExpandPassiveDamages : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ExpandPassiveDamage> data;

	public static _ExpandPassiveDamages Load()
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
