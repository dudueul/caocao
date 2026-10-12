using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _UserSpells : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__UserSpell> data;

	public static _UserSpells Load()
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
