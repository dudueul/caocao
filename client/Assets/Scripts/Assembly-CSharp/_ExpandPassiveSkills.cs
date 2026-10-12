using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ExpandPassiveSkills : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ExpandPassiveSkill> data;

	public static _ExpandPassiveSkills Load()
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
