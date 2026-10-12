using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class Restart : MonoBehaviour
{
	private static List<GameObject> ms_dontDestroyGameObjects;

	public static void AddDontDestroyGameObject(GameObject go)
	{
	}

	public static void RemoveDontDestroyGameObject(GameObject go)
	{
	}

	private void Awake()
	{
	}

	[DebuggerHidden]
	private IEnumerator Start()
	{
		return null;
	}
}
