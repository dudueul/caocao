using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PopupCompetitionReward : CaocaoUIModalPopup
{
	public static bool isAlive;

	[SerializeField]
	private UITexture m_tierIconTexture;

	[SerializeField]
	private UILabelLocalized m_tierNameLabel;

	[SerializeField]
	private UILabelLocalized m_scoreLabel;

	[SerializeField]
	private UILabelLocalized m_rankLabel;

	[SerializeField]
	private Transform m_rewardGrid;

	[SerializeField]
	private GameObject m_noRewardTextGo;

	[SerializeField]
	private UILabelLocalized m_confirmButtonLabel;

	private bool m_noReward;

	private Action m_finishCallback;

	public static void Show(bool noReward, Action finishCallback)
	{
	}

	private void UpdateUi()
	{
	}

	private Dictionary<int, int> GetRewardTimeBoxes(int rewardId)
	{
		return null;
	}

	public void OnClose()
	{
	}

	private void OnGetItem(JsonData response)
	{
	}

	private void ShowGetRewardPopup(JsonData jsonData)
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

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}
