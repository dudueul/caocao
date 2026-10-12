using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class PopupGetState : SelfRegister2UIComponentsMonobehaviour
{
	private enum StatePopupPhase
	{
		ConqueredState = 0,
		ConqueredAllState = 1,
		LostState = 2
	}

	private StatePopupPhase m_popupState;

	private List<RewardInfo> m_rewardItems;

	private bool m_willShowItemPopup;

	private Transform m_mapConquered;

	private Transform m_mapLost;

	private Transform m_mapAll;

	private UILabelLocalized m_getStateLabel;

	private UILabelLocalized m_getStateDownLabelLine1;

	private UILabelLocalized m_getStateDownLabelLine2;

	private UILabelLocalized m_lostStateLabel;

	private bool m_showStaffRoll;

	public static void ShowPopup(List<RewardInfo> rewardItems)
	{
	}

	protected override void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void Init(List<RewardInfo> rewardItems)
	{
	}

	private void Show()
	{
	}

	private void SetConqueredState()
	{
	}

	private void SetConqueredAllState()
	{
	}

	private void SetLostState()
	{
	}

	private void ShowState(Transform state, bool focused)
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowRewardItemPopup(PlayMakerFSM fsm)
	{
		return null;
	}

	private void OnClick()
	{
	}

	public void ClosePopup()
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowStaffRoll()
	{
		return null;
	}
}
