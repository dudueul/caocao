using System;
using UnityEngine;

public class PanelPopupHistory : CaocaoUIModalPopup
{
	private enum Mode
	{
		Watch = 0,
		Reclaim = 1
	}

	private Mode m_mode;

	public UILabelLocalized m_title;

	public PanelUserInfo m_userInfo;

	public UILabelLocalized m_btnLabl;

	public GameObject m_reclaimPanel;

	public UILabelLocalized m_reclaimTitle;

	public PrefabInstance m_silverInst;

	public PrefabInstance m_foodInst;

	private Action m_reclaimCallback;

	public GameObject m_watchPanel;

	public UILabelLocalized m_result;

	public UILabelLocalized m_turn;

	public UILabelLocalized m_size;

	public BattleData battleData { get; private set; }

	public void ShowReclaim(BattleData battleData, Action reclaimCallback)
	{
	}

	public void ShowWatch(BattleData battleData)
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

	private void UpdateUI()
	{
	}

	public void OnClickButton()
	{
	}

	private void Watch()
	{
	}

	private void Reclaim()
	{
	}
}
