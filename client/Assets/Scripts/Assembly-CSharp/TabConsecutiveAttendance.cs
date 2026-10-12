using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class TabConsecutiveAttendance : IDisposable
{
	[SerializeField]
	private GameObject m_tabObj;

	[SerializeField]
	private UIGrid m_grid;

	[SerializeField]
	private UIScrollView m_scrollView;

	private List<ConsecutiveAttendanceListItem> m_listItems;

	private bool m_initialize;

	public void Dispose()
	{
	}

	public void SetActive(bool active)
	{
	}

	public void Initialize()
	{
	}

	private int SortFunc(ConsecutiveAttendanceListItem x, ConsecutiveAttendanceListItem y)
	{
		return 0;
	}

	public void UpdateUI()
	{
	}

	public bool CheckCanGiveReward()
	{
		return false;
	}
}
