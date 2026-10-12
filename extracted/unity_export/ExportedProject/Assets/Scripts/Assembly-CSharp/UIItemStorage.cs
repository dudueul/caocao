using System.Collections.Generic;
using UnityEngine;

public class UIItemStorage : MonoBehaviour
{
	public int maxItemCount;

	public int maxRows;

	public int maxColumns;

	public GameObject template;

	public UIWidget background;

	public int spacing;

	public int padding;

	private List<InvGameItem> mItems;

	public List<InvGameItem> items
	{
		get
		{
			return null;
		}
	}

	public InvGameItem GetItem(int slot)
	{
		return null;
	}

	public InvGameItem Replace(int slot, InvGameItem item)
	{
		return null;
	}

	private void Start()
	{
	}
}
