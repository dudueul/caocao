using System;
using UnityEngine;

public class InstanceEventListItem : MonoBehaviour
{
	private Transform m_selected;

	private Transform m_clear;

	private int m_stateId;

	private UILabelLocalized m_stateLabel;

	private UILabelLocalized m_levelLabel;

	private DayOfWeek m_day;

	private UILabelLocalized m_dayLabel;

	public int StateId
	{
		get
		{
			return 0;
		}
	}

	public DayOfWeek Day
	{
		get
		{
			return DayOfWeek.Sunday;
		}
	}

	public void Initialize(int stateId, DayOfWeek day)
	{
	}

	private void OnDestroy()
	{
	}

	public void SetStateLabelStatus(bool enable)
	{
	}

	public void SetDayLabelStatus(bool enable)
	{
	}

	public void SetVisible(bool visible)
	{
	}

	public void SetClearVisible(bool visible)
	{
	}

	public void SetSelectedVisible(bool visible)
	{
	}

	public void ShowStateListItem()
	{
	}

	public void ShowWeekdayListItem()
	{
	}

	private void CheckStateClear()
	{
	}
}
