using System;
using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class TimeBoxRewardItemUI : MonoBehaviour
{
	private Transform selectTransf;

	public Action<TimeBoxRewardItemUI> onClicked;

	public UserTimeBox m_userTimeBox { get; private set; }

	public int decideIndex { get; private set; }

	public void UpdateUI(UserTimeBox userTimeBox, int decideIndex, Action<TimeBoxRewardItemUI> action)
	{
	}

	private void SetItemUI(Item decideItemPd, int amount, bool needShowReplaceItem)
	{
	}

	private void SetReplaceItemUI(Item replaceItemPd, int amount)
	{
	}

	private void SetItemTierBack(int tier)
	{
	}

	private void SetItemTierBG(int tier)
	{
	}

	private void SetReplaceItemTierBg(int tier)
	{
	}

	private string GetItemTierlName(int tier)
	{
		return null;
	}

	public void OnOpenOne()
	{
	}

	public void ToggleSelected(bool selected)
	{
	}

	public void OnShowItemTooltip(ItemUI itemUI)
	{
	}

	[DebuggerHidden]
	private IEnumerator CheckItemTierBackOpened()
	{
		return null;
	}
}
