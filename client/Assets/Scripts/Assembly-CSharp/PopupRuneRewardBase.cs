using System;
using System.Collections.Generic;
using UnityEngine;

public abstract class PopupRuneRewardBase<T> : CaocaoUIModalPopup where T : PopupRuneRewardBase<T>
{
	[SerializeField]
	protected GameObject m_mainBody;

	[SerializeField]
	protected UIGrid m_grid;

	[SerializeField]
	protected UILabelLocalized m_title;

	[SerializeField]
	protected UILabelLocalized m_desc;

	protected List<int> m_ids;

	protected List<RewardInfo> m_rewards;

	protected Action m_callback;

	protected static void ShowPrefab(GameObject prefab, List<RewardInfo> rewards, Action callback)
	{
	}

	public virtual void UpdateUI()
	{
	}

	protected virtual void OnClickRune(RuneUI runeUI)
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}
}
