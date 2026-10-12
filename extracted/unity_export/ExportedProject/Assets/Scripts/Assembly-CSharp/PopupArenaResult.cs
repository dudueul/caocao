using System;
using UnityEngine;

public class PopupArenaResult : CaocaoUIModalPopup
{
	private Action callback;

	private bool complete;

	private bool perfect;

	private Transform m_stageList;

	private UILabelLocalized m_labelStage;

	private UILabelLocalized m_labelStamp;

	private GameObject m_objClear;

	private GameObject m_objComplete;

	private Transform m_rewardList;

	private Transform m_stampRewardList;

	private Transform m_dimReward;

	private UILabelLocalized m_labelButtonName;

	private Transform stageList
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelStage
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelStamp
	{
		get
		{
			return null;
		}
	}

	private GameObject objClear
	{
		get
		{
			return null;
		}
	}

	private GameObject objComplete
	{
		get
		{
			return null;
		}
	}

	private Transform rewardList
	{
		get
		{
			return null;
		}
	}

	private Transform stampRewardList
	{
		get
		{
			return null;
		}
	}

	private Transform dimReward
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelButtonName
	{
		get
		{
			return null;
		}
	}

	public static void Show(Action callback)
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateStageUI(Transform trs, int level)
	{
	}

	public void OnGetRewardButton()
	{
	}

	protected override void OnDestroy()
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
