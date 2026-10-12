using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GetSkills : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GetSkill> data;

	public static _GetSkills Load()
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
