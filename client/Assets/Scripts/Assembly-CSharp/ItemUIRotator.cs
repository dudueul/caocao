using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

public class ItemUIRotator : MonoBehaviour
{
	private float interval;

	private ItemUI itemUI;

	private int index;

	private List<int> itemIds;

	private int nextItemId
	{
		get
		{
			return 0;
		}
	}

	public void SetItemIds(List<ObscuredInt> ids)
	{
	}

	public void SetItemIds(params int[] ids)
	{
	}

	private void StartRotation()
	{
	}

	private void SetNextItem()
	{
	}

	private void OnDisable()
	{
	}

	private void OnEnable()
	{
	}
}
