using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _CompetitionMaps : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__CompetitionMap> data;

	public static _CompetitionMaps Load()
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
