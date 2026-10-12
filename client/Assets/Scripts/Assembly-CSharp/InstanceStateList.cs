using System;
using System.Collections.Generic;
using UnityEngine;

public class InstanceStateList : MonoBehaviour
{
	public delegate void Callback(InstanceEventListItem listItem);

	private Callback m_callback;

	private UILabelLocalized m_title;

	private UIScrollView m_scrollView;

	private UIGrid m_grid;

	private List<InstanceEventListItem> m_listItemList;

	private GameObject m_listItemTemplate;

	private bool m_initialized;

	public void Initialize(PanelInstanceLobby parent)
	{
	}

	private void OnDestroy()
	{
	}

	public void SetCallback(Callback callback)
	{
	}

	private void ListItemClicked(InstanceEventListItem listItem)
	{
	}

	private void CloseButtonClicked()
	{
	}

	public void ShowNormalList(int stateId, Callback callback)
	{
	}

	public void ShowSpecialList(DayOfWeek day, Callback callback)
	{
	}

	public void Hide()
	{
	}
}
