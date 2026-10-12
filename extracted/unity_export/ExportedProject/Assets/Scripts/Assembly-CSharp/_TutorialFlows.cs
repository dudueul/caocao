using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _TutorialFlows : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__TutorialFlow> data;

	public static _TutorialFlows Load()
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
