using System;
using UnityEngine;

public class RuneDecompositionListItem : RuneSlotUI
{
	public GameObject objSelect;

	private Action<RuneDecompositionListItem> onSelect;

	public UserRuneItem runeItem { get; private set; }

	public bool selected { get; private set; }

	public void Init(UserRuneItem runeItem, Action<RuneDecompositionListItem> onSelect)
	{
	}

	private new void UpdateUI()
	{
	}

	protected override void UpdateRuneUI()
	{
	}

	protected override void UpdatePassiveUI()
	{
	}

	private void OnClick()
	{
	}

	public void ForceSelect(bool select)
	{
	}

	private void OnDestroy()
	{
	}
}
