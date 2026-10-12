using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ArenaNPCFormations : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ArenaNPCFormation> data;

	public static _ArenaNPCFormations Load()
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
