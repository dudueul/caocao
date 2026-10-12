using System;
using UnityEngine;

public class PanelInventoryListSort : MonoBehaviour
{
	[SerializeField]
	private GameObject sortItem;

	private bool initiated;

	private void OnClick()
	{
	}

	public void Show(EnumData.InvenItemSortType focus, Action<EnumData.InvenItemSortType> callback)
	{
	}

	public void Close()
	{
	}
}
