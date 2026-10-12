using System.Collections.Generic;
using UnityEngine;

public class GameObjectPool : SceneSingleton<GameObjectPool>
{
	private class Pool
	{
		public string poolName;

		public GameObject prefab;

		public bool isEffect;

		public Stack<GameObject> unusedObjects;

		public Dictionary<int, GameObject> usedObjects;

		public Pool(string poolName, GameObject prefab, bool isEffect)
		{
		}
	}

	private Dictionary<int, Dictionary<string, Pool>> m_pools;

	public Transform parentTrans { get; private set; }

	private void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	public bool IsExist(string poolName, int layer = 0)
	{
		return false;
	}

	public void MakePool(string poolName, GameObject prefab, bool isEffect, int initialCount, int layer = 0)
	{
	}

	public GameObject GetObject(string poolName, int layer = 0)
	{
		return null;
	}

	public bool DisposeObject(GameObject obj)
	{
		return false;
	}

	public bool DisposeObject(string poolName, GameObject obj, int layer = 0)
	{
		return false;
	}

	private void Allocate(Pool pool, int count)
	{
	}
}
