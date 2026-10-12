using System;
using UnityEngine;

public class GuildUIUserListBase : MonoBehaviour
{
	public GuildUIMemberListObject memberList;

	public Action<ulong, int> onSelectedItem;

	public Action<ulong, int> onReselectedItem;

	protected virtual void OnEnable()
	{
	}

	protected virtual void OnSelectItem()
	{
	}

	protected virtual void OnReselectItem()
	{
	}
}
