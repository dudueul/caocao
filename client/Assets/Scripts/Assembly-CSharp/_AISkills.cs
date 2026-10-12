using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _AISkills : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__AISkill> data;

	public static _AISkills Load()
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
